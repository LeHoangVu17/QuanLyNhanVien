package QuanLyNhanVien;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class NhanVien implements ITinhThue{
    protected String maNV;
    
	protected String hoTen;
    protected String soCCCD;
    protected Date ngayVaoCT;
    protected Scanner sc = new Scanner(System.in);

    public NhanVien() {
    }


    public NhanVien(String maNV, String hoTen, String soCCCD, Date ngayVaoCT) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.soCCCD = soCCCD;
        this.ngayVaoCT = ngayVaoCT;
        
        
    }
    public String getMaNV() {
		return maNV;
	}


	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}


	public String getHoTen() {
		return hoTen;
	}


	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}


	public String getSoCCCD() {
		return soCCCD;
	}


	public void setSoCCCD(String soCCCD) {
		this.soCCCD = soCCCD;
	}


	public Date getNgayVaoCT() {
		return ngayVaoCT;
	}


	public void setNgayVaoCT(Date ngayVaoCT) {
		this.ngayVaoCT = ngayVaoCT;
	}


	public Scanner getSc() {
		return sc;
	}


	public void setSc(Scanner sc) {
		this.sc = sc;
	}


    public void nhapThongTin(String maNV) {
        this.maNV = maNV; // Gán mã NV đã được kiểm tra từ lớp quản lý
        try {
            System.out.print("Nhập họ tên: ");
            hoTen = sc.nextLine();
            System.out.print("Nhập số CCCD: ");
            soCCCD = sc.nextLine();
            System.out.print("Nhập ngày vào công ty (dd/MM/yyyy): ");
            String ngay = sc.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            ngayVaoCT = sdf.parse(ngay);
        } catch (Exception e) {
            System.out.println("Lỗi nhập dữ liệu: " + e.getMessage());
            ngayVaoCT = new Date(); // Gán ngày mặc định nếu lỗi
        }
    }

    public void xuatThongTin() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Mã NV: " + maNV);
        System.out.println("Họ tên: " + hoTen);
        System.out.println("Số CCCD: " + soCCCD);
        System.out.println("Ngày vào công ty: " + sdf.format(ngayVaoCT));
        System.out.println("Thâm niên: " + tinhThamNien() + " năm");
    }

    public int tinhThamNien() {
        Date ngayHienTai = new Date();
        if (ngayVaoCT == null || ngayVaoCT.after(ngayHienTai)) {
            return 0;
        }
        long khoangCach = ngayHienTai.getTime() - ngayVaoCT.getTime();
        long soNgay = khoangCach / (1000L * 60 * 60 * 24); 
        int soNam = (int) (soNgay / 365);
        return soNam;
    }

    public abstract double tinhLuong();
    public abstract double tinhPhuCap();
    public abstract double tinhThue();
}