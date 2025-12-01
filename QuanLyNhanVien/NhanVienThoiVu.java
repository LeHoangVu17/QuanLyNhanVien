package QuanLyNhanVien;
import java.util.Date; 
public class NhanVienThoiVu extends NhanVien  {
    private int soNgayLam;
	private double tienCongNgay;
	public int getSoNgayLam() {
		return soNgayLam;
	}


	public void setSoNgayLam(int soNgayLam) {
		this.soNgayLam = soNgayLam;
	}


	public double getTienCongNgay() {
		return tienCongNgay;
	}


	public void setTienCongNgay(double tienCongNgay) {
		this.tienCongNgay = tienCongNgay;
	}

    public NhanVienThoiVu() {
  
    }

   
    public NhanVienThoiVu(String maNV, String hoTen, String soCCCD, Date ngayVaoCT,int soNgayLam, double tienCongNgay) {
        super(maNV, hoTen, soCCCD, ngayVaoCT);
        this.soNgayLam = soNgayLam;
        this.tienCongNgay = tienCongNgay;
    }


    @Override
    public void nhapThongTin(String maNV) {
        super.nhapThongTin(maNV); 
        
        // Nhập Số Ngày Làm (Bắt buộc số nguyên >= 0)
        while (true) {
            try {
                System.out.print("Nhập số ngày làm: ");
                int ngay = Integer.parseInt(sc.nextLine());
                if (ngay < 0) throw new NumberFormatException();
                setSoNgayLam(ngay);
                break;
            } catch (NumberFormatException e) {
                System.out.println(">>> Lỗi: Số ngày phải là số nguyên dương! Nhập lại.");
            }
        }

        // Nhập Tiền Công (Bắt buộc số > 0)
        while (true) {
            try {
                System.out.print("Nhập tiền công 1 ngày: ");
                double tien = Double.parseDouble(sc.nextLine());
                if (tien < 0) throw new NumberFormatException();
                setTienCongNgay(tien);
                break;
            } catch (NumberFormatException e) {
                System.out.println(">>> Lỗi: Tiền công phải là số dương! Nhập lại.");
            }
        }
    }

    @Override
    public double tinhPhuCap() {
        if (soNgayLam > 26)
            return 500000;
        else
            return 0;
    }

    @Override
    public double tinhThue() {
        double thuNhap = soNgayLam * tienCongNgay;
        if (thuNhap > 15000000)
            return thuNhap * 0.05; // 5%
        else
            return 0;
    }

    @Override
    public double tinhLuong() {
        double luongTruocThue;
        if (tinhThamNien() >= 3)
            luongTruocThue = soNgayLam * tienCongNgay + tinhPhuCap() + 300000;
        else
            luongTruocThue = soNgayLam * tienCongNgay + tinhPhuCap();
        
        return luongTruocThue - tinhThue();
    }
    @Override
    public void xuatThongTin() {
        super.xuatThongTin();
        System.out.println("Loại: Nhân viên thời vụ");
        System.out.println("Số ngày làm: " + getSoNgayLam());
        System.out.printf("Tiền công mỗi ngày: %,.0f VNĐ%n", getTienCongNgay());
        System.out.printf("Phụ cấp: %,.0f VNĐ%n", tinhPhuCap());
        System.out.printf("Thuế thu nhập: %,.0f VNĐ%n", tinhThue());
        System.out.printf("Lương thực lĩnh: %,.0f VNĐ%n", tinhLuong());
    }
}