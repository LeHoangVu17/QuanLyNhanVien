package QuanLyNhanVien;
import java.util.ArrayList;
import java.util.Scanner;

public class PhongBan {
    private String maPhong;
    private String tenPhong;
    // Quan hệ Aggregation: Danh sách này chỉ "tham chiếu"
    private ArrayList<NhanVien> dsNhanVienTrongPhong;
    private Scanner sc;


    public PhongBan() {
        this.dsNhanVienTrongPhong = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public PhongBan(String maPhong, String tenPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.dsNhanVienTrongPhong = new ArrayList<>();
    }

    public String getMaPhong() {
        return maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    
    public void nhapThongTin(String maPhong) {
    		this.maPhong = maPhong;
            System.out.print("Nhập tên phòng ban: ");
            this.tenPhong = sc.nextLine();
    }

   
    public void xuatThongTin() {
        System.out.println("\n--- Phòng: " + tenPhong + " (Mã: " + maPhong + ") ---");
        if (dsNhanVienTrongPhong.isEmpty()) {
            System.out.println("Chưa có nhân viên nào trong phòng này.");
            return;
        }

        System.out.println("Danh sách nhân viên:");
        for (NhanVien nv : dsNhanVienTrongPhong) {
            // Chỉ hiển thị thông tin cơ bản của nhân viên
            System.out.println("- Mã NV: " + nv.maNV + " | Tên: " + nv.hoTen);
        }
    }

    /**
     * Phương thức nghiệp vụ (Aggregation)
     * Nhận một NhanVien từ bên ngoài và thêm vào danh sách riêng
     */
    public void themNhanVien(NhanVien nv) {
        // Có thể thêm logic kiểm tra xem nv đã có trong list chưa
        if (!dsNhanVienTrongPhong.contains(nv)) {
            dsNhanVienTrongPhong.add(nv);
        } else {
            System.out.println("Nhân viên " + nv.hoTen + " đã ở trong phòng này rồi.");
        }
    }
}