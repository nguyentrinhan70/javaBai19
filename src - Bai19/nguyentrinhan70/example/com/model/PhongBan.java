package nguyentrinhan70.example.com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class PhongBan implements Serializable{
	private String maPhongBan;
	private String tenPhongBan;
	private Vector<NhanVien> nhanviens;
	
	public void themNhanVien(NhanVien nv) {
		this.nhanviens.add(nv);
		nv.setPhongBan(this);
	}
	
	public PhongBan() {
		super();
		this.nhanviens= new Vector<NhanVien>();
	}
	public String getMaPhongBan() {
		return maPhongBan;
	}
	public void setMaPhongBan(String maPhongBan) {
		this.maPhongBan = maPhongBan;
	}
	public String getTenPhongBan() {
		return tenPhongBan;
	}
	public void setTenPhongBan(String tenPhongBan) {
		this.tenPhongBan = tenPhongBan;
	}
	public Vector<NhanVien> getNhanviens() {
		return nhanviens; 
	}
	public void setNhanviens(Vector<NhanVien> nhanviens) {
		this.nhanviens = nhanviens;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.tenPhongBan;
	}
}
