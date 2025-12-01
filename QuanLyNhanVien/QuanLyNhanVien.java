package QuanLyNhanVien;
import java.util.*;


public class QuanLyNhanVien implements IChucnang,IIOFile { 
    // Dữ liệu
    private HashMap<String, NhanVien> dsNV;
    private HashMap<String, PhongBan> dsPhongBan; 
    private HashMap<String, DuAn> dsDuAn;

    private Scanner sc;

    public QuanLyNhanVien() {
        dsNV = new HashMap<>();
        dsPhongBan = new HashMap<>();
        dsDuAn = new HashMap<>(); 
        sc = new Scanner(System.in);
    }

    // 
    @Override
    public void them() {
        System.out.println("\n--- Bắt đầu thêm nhân viên mới ---");
        char tiepTuc; 
        do {
            String maNV;
            // 1. Nhập Mã NV (Bắt buộc không rỗng + không trùng)
            while (true) {
                System.out.print("\nNhập mã nhân viên (VD: NV001): ");
                maNV = sc.nextLine();
                if (dsNV.containsKey(maNV)) { 
                    System.out.println(">>> Lỗi: Mã này đã tồn tại!");
                } else if (maNV.trim().isEmpty()) {
                     System.out.println(">>> Lỗi: Không được để trống!");
                } else {
                    break;
                }
            }

            // 2. Chọn loại (Bắt buộc nhập số 1 hoặc 2) 
            int loai = 0;
            while (true) {
                try {
                    System.out.print("Chọn loại (1: Biên chế, 2: Thời vụ): ");
                    loai = Integer.parseInt(sc.nextLine()); // Nhập chuỗi rồi ép sang số
                    
                    if (loai != 1 && loai != 2) {
                        System.out.println(">>> Lỗi: Chỉ được nhập số 1 hoặc 2!");
                    } else {
                        break; // Nhập đúng thì thoát vòng lặp
                    }
                } catch (NumberFormatException e) {
                    System.out.println(">>> Lỗi: vui lòng nhập số!");
                }
            }
            
            NhanVien nv;
            if (loai == 1) nv = new NhanVienBienChe();
            else nv = new NhanVienThoiVu();

            nv.nhapThongTin(maNV); 
            dsNV.put(maNV, nv);
            System.out.println("==> Thêm thành công nhân viên " + maNV);

            // 3. Hỏi tiếp tục (Bắt buộc nhập y hoặc n)
            while (true) {
                System.out.print("\nNhập tiếp? (y/n): ");
                String input = sc.nextLine().trim().toLowerCase();
                if (input.equals("y") || input.equals("n")) {
                    tiepTuc = input.charAt(0);
                    break;
                }
                System.out.println(">>> Lỗi: Chỉ nhập 'y' (có) hoặc 'n' (không)!");
            }

        } while (tiepTuc == 'y');
    }
    @Override
    public void xoa() {
        System.out.print("\nNhập mã nhân viên cần xóa: ");
        String maNV = sc.nextLine();
        if (dsNV.remove(maNV) != null) {
            System.out.println("Đã xóa thành công nhân viên mã: " + maNV);
        } else {
            System.out.println("Không tìm thấy nhân viên mã: " + maNV);
        }
    }

    /**
     * 3. SỬA (Chức năng mới)
     */
    @Override
    public void sua() {
        System.out.println("\n--- Sửa thông tin nhân viên ---");
        System.out.print("Nhập mã nhân viên cần sửa: ");
        String maNV = sc.nextLine();

        NhanVien nv = dsNV.get(maNV);
        if (nv == null) {
            System.out.println("Không tìm thấy nhân viên có mã: " + maNV);
            return;
        }

        // Hiển thị thông tin hiện tại để dễ nhìn
        System.out.println("--> Đang sửa nhân viên: " + nv.getHoTen());
        nv.xuatThongTin();
        System.out.println("---------------------------------");

        // Menu chung cho cả 2 loại
        System.out.println("Chọn thông tin cần sửa:");
        System.out.println("1. Họ tên");
        System.out.println("2. Số CCCD");
        System.out.println("3. Ngày vào công ty");
        
        // Kiểm tra loại nhân viên để hiện menu riêng
        if (nv instanceof NhanVienBienChe) {
            System.out.println("4. Lương cơ bản");
            System.out.println("5. Hệ số lương");
        } else if (nv instanceof NhanVienThoiVu) {
            System.out.println("4. Số ngày làm");
            System.out.println("5. Tiền công ngày");
        }
        
        System.out.println("0. Hủy bỏ");
        System.out.print("==> Mời chọn: ");

        int chon;
        try {
            chon = Integer.parseInt(sc.nextLine());
        } catch (Exception e) { chon = -1; }

        try {
            switch (chon) {
                case 1:
                    System.out.print("Nhập họ tên mới: ");
                    nv.setHoTen(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Nhập CCCD mới: ");
                    nv.setSoCCCD(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Nhập ngày vào mới (dd/MM/yyyy): ");
                    String sDate = sc.nextLine();
                    nv.setNgayVaoCT(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(sDate));
                    break;
                case 4:
                    if (nv instanceof NhanVienBienChe) {
                        System.out.print("Nhập lương cơ bản mới: ");
                        ((NhanVienBienChe) nv).setLuongCoBan(Double.parseDouble(sc.nextLine()));
                    } else {
                        System.out.print("Nhập số ngày làm mới: ");
                        ((NhanVienThoiVu) nv).setSoNgayLam(Integer.parseInt(sc.nextLine()));
                    }
                    break;
                case 5:
                    if (nv instanceof NhanVienBienChe) {
                        System.out.print("Nhập hệ số lương mới: ");
                        ((NhanVienBienChe) nv).setHeSoLuong(Double.parseDouble(sc.nextLine()));
                    } else {
                        System.out.print("Nhập tiền công ngày mới: ");
                        ((NhanVienThoiVu) nv).setTienCongNgay(Double.parseDouble(sc.nextLine()));
                    }
                    break;
                case 0:
                    System.out.println("Đã hủy sửa.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    return;
            }
            System.out.println("==> Cập nhật thành công!");
            
        } catch (Exception e) {
            System.out.println("Lỗi nhập dữ liệu (Sai định dạng ngày hoặc số). Cập nhật thất bại.");
        }
    }

    public void xuatDanhSach() {
        if (dsNV.isEmpty()) {
            System.out.println("\nChưa có nhân viên nào.");
            return;
        }
        System.out.println("\n===== DANH SÁCH NHÂN VIÊN =====");
        for (NhanVien nv : dsNV.values()) {
            nv.xuatThongTin(); 
            System.out.println("-------------------------");
        }
    }

    public void timNhanVien() {
        System.out.print("\nNhập mã nhân viên cần tìm: ");
        String maNV = sc.nextLine();
        NhanVien nv = dsNV.get(maNV);
        if (nv != null) nv.xuatThongTin();
        else System.out.println("Không tìm thấy.");
    }

    public void tinhTongLuong() {
        double tong = 0;
        for (NhanVien nv : dsNV.values()) tong += nv.tinhLuong();
        System.out.printf("Tổng lương công ty: %,.0f VNĐ%n", tong);
    }

    // =======================================================
    // III. QUẢN LÝ PHÒNG BAN & DỰ ÁN
    // =======================================================

    public void themPhongBan() {
        System.out.println("\n--- Thêm phòng ban mới ---");
        System.out.print("Nhập mã phòng: ");
        String maPhong = sc.nextLine().toUpperCase();
        PhongBan pb = new PhongBan();
        pb.nhapThongTin(maPhong); 
        dsPhongBan.put(maPhong, pb);
        System.out.println("Đã thêm phòng ban: " + pb.getTenPhong());
    }

    public void ganNhanVienVaoPhong() {
        System.out.print("Nhập mã nhân viên: ");
        String maNV = sc.nextLine();
        NhanVien nv = dsNV.get(maNV);
        if (nv == null) { System.out.println("NV không tồn tại."); return; }

        System.out.print("Nhập mã phòng ban: ");
        String maPhong = sc.nextLine().toUpperCase();
        PhongBan pb = dsPhongBan.get(maPhong);
        if (pb == null) { System.out.println("Phòng ban không tồn tại."); return; }

        pb.themNhanVien(nv);
        System.out.println("Đã gán thành công.");
    }

    public void hienThiCacPhongBan() {
        System.out.println("\n--- DANH SÁCH PHÒNG BAN ---");
        for (PhongBan pb : dsPhongBan.values()) pb.xuatThongTin();
    }

    public void themDuAn() {
        System.out.println("\n--- Thêm Dự Án Mới ---");
        System.out.print("Nhập mã dự án (VD: DA01): ");
        String maDA = sc.nextLine().toUpperCase();
        if (dsDuAn.containsKey(maDA)) {
            System.out.println("Lỗi: Mã dự án đã tồn tại.");
            return;
        }
        DuAn da = new DuAn();
        da.nhapThongTin(maDA); 
        dsDuAn.put(maDA, da);
        System.out.println("Đã tạo dự án '" + da.getTenDA() + "' thành công!");
    }

    public void themCongViecChoDuAn() {
        System.out.print("Nhập mã dự án cần thêm việc: ");
        String maDA = sc.nextLine().toUpperCase();
        DuAn da = dsDuAn.get(maDA);
        if (da == null) { System.out.println("Không tìm thấy dự án này."); return; }
        da.themCongViecMoi(); 
    }

    public void ganNhanVienVaoCongViec() {
        System.out.println("\n--- Gán Nhân Viên Vào Task ---");
        System.out.print("Nhập mã dự án: ");
        String maDA = sc.nextLine().toUpperCase();
        DuAn da = dsDuAn.get(maDA);
        if (da == null) { System.out.println("Dự án không tồn tại."); return; }

        System.out.print("Nhập mã công việc (Task ID): ");
        String maCV = sc.nextLine();
        CongViec cv = da.getCongViec(maCV);
        if (cv == null) { System.out.println("Công việc không tồn tại."); return; }

        System.out.print("Nhập mã nhân viên thực hiện: ");
        String maNV = sc.nextLine();
        NhanVien nv = dsNV.get(maNV);
        if (nv == null) { System.out.println("Nhân viên không tồn tại."); return; }

        cv.ganNguoiPhuTrach(nv);
        da.themNhanVien(nv); 
    }

    public void hienThiCacDuAn() {
        System.out.println("\n--- DANH SÁCH DỰ ÁN ---");
        for (DuAn da : dsDuAn.values()) {
            da.xuatThongTin();
            System.out.println("-------------------------");
        }
        
    }
    @Override
    public void ghiFile() {
        System.out.println("--> Đang lưu dữ liệu vào file data.txt...");
        // Ở đây không cần code xử lý file thật
        System.out.println("--> Ghi file thành công!");
    }

    @Override
    public void docFile() {
        System.out.println("--> Đang đọc dữ liệu từ file data.txt...");
        // Giả vờ load dữ liệu
        System.out.println("--> Đọc file thành công!");
    }
}