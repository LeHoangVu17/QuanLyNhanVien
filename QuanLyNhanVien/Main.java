package QuanLyNhanVien; // Nhớ sửa package nếu bạn đặt tên khác
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Khởi tạo "bộ não" quản lý
        QuanLyNhanVien ql = new QuanLyNhanVien();
        int luaChon;

        do {
            System.out.println("\n================ MENU QUẢN LÝ TỔNG HỢP ================");
            
            // --- NHÓM 1: QUẢN LÝ NHÂN VIÊN (CRUD Interface) ---
            System.out.println("--- [Quản Lý Nhân Viên] ---");
            System.out.println("1. Thêm nhân viên mới (Create)");
            System.out.println("2. Sửa thông tin nhân viên (Update)");
            System.out.println("3. Xóa nhân viên (Delete)");
            System.out.println("4. Tìm kiếm nhân viên");
            System.out.println("5. Xuất danh sách nhân viên");
            System.out.println("6. Tính tổng lương toàn công ty");

            // --- NHÓM 2: QUẢN LÝ PHÒNG BAN ---
            System.out.println("\n--- [Quản Lý Phòng Ban] ---");
            System.out.println("7. Thêm phòng ban mới");
            System.out.println("8. Gán nhân viên vào phòng ban");
            System.out.println("9. Hiển thị danh sách các phòng ban");

            // --- NHÓM 3: QUẢN LÝ DỰ ÁN & CÔNG VIỆC ---
            System.out.println("\n--- [Quản Lý Dự Án & Công Việc] ---");
            System.out.println("10. Thêm dự án mới");
            System.out.println("11. Thêm công việc (Task) cho dự án");
            System.out.println("12. Gán nhân viên vào công việc");
            System.out.println("13. Hiển thị danh sách dự án");
            System.out.println("14. Ghi file (Demo)");
            System.out.println("15. Đọc file (Demo)");
            System.out.println("\n0. Thoát chương trình");
            System.out.println("=======================================================");
            System.out.print("==> Vui lòng chọn chức năng (0-15): ");
            
            try {
                luaChon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Lựa chọn không hợp lệ, vui lòng nhập số.");
                luaChon = -1; 
            }

            switch (luaChon) {
                // --- NHÂN VIÊN ---
                case 1:
                    ql.them(); // Gọi từ Interface
                    break;
                case 2:
                    ql.sua();  // Gọi từ Interface (Mới thêm)
                    break;
                case 3:
                    ql.xoa();  // Gọi từ Interface
                    break;
                case 4:
                    ql.timNhanVien();
                    break;
                case 5:
                    ql.xuatDanhSach();
                    break;
                case 6:
                    ql.tinhTongLuong();
                    break;

                // --- PHÒNG BAN ---
                case 7:
                    ql.themPhongBan();
                    break;
                case 8:
                    ql.ganNhanVienVaoPhong();
                    break;
                case 9:
                    ql.hienThiCacPhongBan();
                    break;

                // --- DỰ ÁN & CÔNG VIỆC (MỚI) ---
                case 10:
                    ql.themDuAn();
                    break;
                case 11:
                    ql.themCongViecChoDuAn();
                    break;
                case 12:
                    ql.ganNhanVienVaoCongViec();
                    break;
                case 13:
                    ql.hienThiCacDuAn();
                    break;
                case 14:
                    ql.ghiFile(); 
                    break;
                case 15:
                    ql.docFile();
                    break;    
                case 0:
                    System.out.println("Đang thoát chương trình... Cảm ơn!");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng chọn từ 0 đến 13.");
            }
        } while (luaChon != 0);
        
        sc.close();
    }
}