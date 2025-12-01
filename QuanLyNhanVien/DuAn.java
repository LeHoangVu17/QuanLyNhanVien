package QuanLyNhanVien;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class DuAn {
    // Thuộc tính private
    private String maDA;
    private String tenDA;
    private double nganSach;
    
    // Aggregation (với NhanVien) - Không làm getter/setter
    private ArrayList<NhanVien> dsNhanVienTrongDuAn;
    
    // Composition (với CongViec) - Không làm getter/setter
    private HashMap<String, CongViec> dsCongViec; 

    private Scanner sc;

    // === CONSTRUCTOR ===
    public DuAn() {
        this.dsNhanVienTrongDuAn = new ArrayList<>();
        this.dsCongViec = new HashMap<>(); 
        this.sc = new Scanner(System.in);
    }

    // === GETTERS & SETTERS (Chỉ cho thuộc tính cơ bản) ===
    public String getMaDA() {
        return maDA;
    }

    public void setMaDA(String maDA) {
        this.maDA = maDA;
    }

    public String getTenDA() {
        return tenDA;
    }

    public void setTenDA(String tenDA) {
        this.tenDA = tenDA;
    }

    public double getNganSach() {
        return nganSach;
    }

    public void setNganSach(double nganSach) {
        this.nganSach = nganSach;
    }

    // === PHƯƠNG THỨC NHẬP XUẤT ===

    // Hàm nhập
    public void nhapThongTin(String maDA) {
        this.maDA = maDA;
        try {
            System.out.print("Nhập tên dự án: ");
            this.tenDA = sc.nextLine();
            System.out.print("Nhập ngân sách dự án (VNĐ): ");
            this.nganSach = Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Lỗi nhập thông tin dự án, gán ngân sách = 0.");
            this.nganSach = 0;
        }
    }

    // Hàm xuất
    public void xuatThongTin() {
        System.out.println("\n--- Dự Án: " + tenDA + " (Mã: " + maDA + ") ---");
        System.out.printf("Ngân sách: %,.0f VNĐ%n", nganSach);
        
        // Xuất danh sách nhân viên (Aggregation)
        if (!dsNhanVienTrongDuAn.isEmpty()) {
            System.out.println("Thành viên dự án:");
            for (NhanVien nv : dsNhanVienTrongDuAn) {
                System.out.print(nv.getMaNV() + " "); // In gọn
            }
            System.out.println();
        }

        // Xuất danh sách công việc (Composition)
        if (dsCongViec.isEmpty()) {
            System.out.println("Chưa có công việc (task) nào trong dự án.");
        } else {
            System.out.println("Danh sách công việc (Tasks):");
            for (CongViec cv : dsCongViec.values()) {
                cv.xuatThongTin();
            }
        }
    }
    
    // === PHƯƠNG THỨC NGHIỆP VỤ ===

    // 1. Thêm nhân viên vào dự án (Aggregation)
    public void themNhanVien(NhanVien nv) {
        if (!dsNhanVienTrongDuAn.contains(nv)) {
            dsNhanVienTrongDuAn.add(nv);
        }
    }

    // 2. Thêm công việc mới (Composition)
    public void themCongViecMoi() {
        System.out.println("\n--- Thêm Task mới cho dự án [" + this.tenDA + "] ---");
        System.out.print("Nhập mã công việc (VD: TASK01): ");
        String maCV = sc.nextLine();
        
        if (dsCongViec.containsKey(maCV)) {
            System.out.println("Mã công việc đã tồn tại.");
            return;
        }
        
        CongViec cv = new CongViec();
        cv.nhapThongTin(maCV); // Gọi hàm nhập của CongViec
        
        dsCongViec.put(maCV, cv); // Thêm vào danh sách sở hữu
        System.out.println("Đã thêm Task '" + cv.getTenCV() + "' vào dự án.");
    }

    // 3. Lấy công việc để gán người (Hỗ trợ nghiệp vụ)
    public CongViec getCongViec(String maCV) {
        return dsCongViec.get(maCV);
    }
}