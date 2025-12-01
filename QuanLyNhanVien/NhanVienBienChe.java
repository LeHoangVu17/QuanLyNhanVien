package QuanLyNhanVien;
import java.util.Date; 

public class NhanVienBienChe extends NhanVien  {
    private double heSoLuong;
	private double luongCoBan;
	public double getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(double heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	public double getLuongCoBan() {
		return luongCoBan;
	}

	public void setLuongCoBan(double luongCoBan) {
		this.luongCoBan = luongCoBan;
	}
    public NhanVienBienChe() {
       
    }

    public NhanVienBienChe(String maNV, String hoTen, String soCCCD, Date ngayVaoCT, double luongCoBan, double heSoLuong) {

        super(maNV, hoTen, soCCCD, ngayVaoCT);
        this.heSoLuong = heSoLuong;
        this.luongCoBan=luongCoBan;
    }
 


    @Override
    public void nhapThongTin(String maNV) {
        super.nhapThongTin(maNV); // Gọi cha nhập tên, ngày...
        
        // Nhập Lương Cơ Bản (Bắt buộc số > 0)
        while (true) {
            try {
                System.out.print("Nhập lương cơ bản: ");
                double luong = Double.parseDouble(sc.nextLine());
                if (luong < 0) throw new NumberFormatException();
                setLuongCoBan(luong);
                break;
            } catch (NumberFormatException e) {
                System.out.println(">>> Lỗi: Lương phải là số dương! Nhập lại.");
            }
        }

        // Nhập Hệ Số Lương (Bắt buộc số > 0)
        while (true) {
            try {
                System.out.print("Nhập hệ số lương: ");
                double heSo = Double.parseDouble(sc.nextLine());
                if (heSo <= 0) throw new NumberFormatException();
                setHeSoLuong(heSo);
                break;
            } catch (NumberFormatException e) {
                System.out.println(">>> Lỗi: Hệ số phải là số dương! Nhập lại.");
            }
        }
    }

    @Override
    public double tinhPhuCap() {
        if (tinhThamNien() >= 10)
            return 0.1 * luongCoBan + 500000;
        else
            return 0.05 * luongCoBan;
    }

    @Override
    public double tinhThue() {
        double thuNhap = heSoLuong * luongCoBan;
        if (thuNhap > 20000000)
            return thuNhap * 0.1; // 10%
        else if (thuNhap > 10000000)
            return thuNhap * 0.05; // 5%
        else
            return 0;
    }

    @Override
    public double tinhLuong() {
        double luongTruocThue;
        if (tinhThamNien() > 5)
            luongTruocThue = heSoLuong * luongCoBan + tinhPhuCap() + 1000000;
        else
            luongTruocThue = heSoLuong * luongCoBan + tinhPhuCap();
        
        return luongTruocThue - tinhThue(); 
    }

    @Override
    public void xuatThongTin() {
        super.xuatThongTin(); 
        System.out.println("Loại: Nhân viên biên chế");
        System.out.printf("Lương cơ bản: %,.0f VNĐ%n", getLuongCoBan());
        System.out.printf("Hệ số lương: %.2f%n", getHeSoLuong()); 
        System.out.printf("Phụ cấp: %,.0f VNĐ%n", tinhPhuCap());
        System.out.printf("Thuế thu nhập: %,.0f VNĐ%n", tinhThue());
        System.out.printf("Lương thực lĩnh: %,.0f VNĐ%n", tinhLuong());
    }
}