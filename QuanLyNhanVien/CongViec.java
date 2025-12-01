package QuanLyNhanVien;

import java.util.Scanner;

public class CongViec {
    // Thuộc tính private
    private String maCV;
    private String tenCV;
    private String trangThai; // "Chưa làm", "Đang làm", "Hoàn thành"
    
    // AGGREGATION: Tham chiếu đến nhân viên (bảng khác)
    private NhanVien nguoiPhuTrach; 
    
    private Scanner sc = new Scanner(System.in);

    // === CONSTRUCTOR ===
    public CongViec() {
        this.trangThai = "Chưa làm"; // Trạng thái mặc định
    }

    // === GETTERS & SETTERS (Cho thuộc tính thường) ===
    public String getMaCV() {
        return maCV;
    }

    public void setMaCV(String maCV) {
        this.maCV = maCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public NhanVien getNguoiPhuTrach() {
        return nguoiPhuTrach;
    }

    // Setter này đặc biệt: khi gán người làm, trạng thái chuyển sang "Đang làm"
    public void setNguoiPhuTrach(NhanVien nv) {
        this.nguoiPhuTrach = nv;
        if (nv != null) {
            this.trangThai = "Đang làm";
        }
    }

    // === PHƯƠNG THỨC NHẬP XUẤT ===

    // Hàm nhập (Nhận mã CV từ DuAn truyền vào)
    public void nhapThongTin(String maCV) {
        this.maCV = maCV;
        System.out.print("Nhập tên công việc (Task): ");
        this.tenCV = sc.nextLine();
    }

    // Hàm xuất
    public void xuatThongTin() {
        System.out.println("  -> Mã CV: " + this.maCV + " [" + this.trangThai + "]");
        System.out.println("     Tên Task: " + this.tenCV);
        
        // Kiểm tra xem có người phụ trách chưa (Aggregation)
        if (this.nguoiPhuTrach != null) {
            System.out.println("     Người phụ trách: " + this.nguoiPhuTrach.getHoTen() + 
                               " (Mã: " + this.nguoiPhuTrach.getMaNV() + ")"); 
                               // Lưu ý: Giả sử NhanVien đã có getter, nếu chưa thì dùng .hoTen, .maNV
        } else {
            System.out.println("     Người phụ trách: (Chưa gán)");
        }
    }

    // === PHƯƠNG THỨC NGHIỆP VỤ ===
    
    // Gán nhân viên vào làm việc (Aggregation)
    public void ganNguoiPhuTrach(NhanVien nv) {
        this.setNguoiPhuTrach(nv); // Gọi setter để cập nhật trạng thái luôn
        System.out.println("Đã gán nhân viên [" + nv.getHoTen() + "] vào công việc: " + this.tenCV);
    }
}