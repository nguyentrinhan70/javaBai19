package nguyentrinhan70.example.com.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import nguyentrinhan70.example.com.model.NhanVien;
import nguyentrinhan70.example.com.model.PhongBan;

public class QuanLyNhanVienUI extends JFrame {
	JComboBox<PhongBan> cboPhongBan;
	JList<NhanVien> listNhanVien;
	JTextField txtMa, txtTen, txtNgayVaoLam, txtNamSinh;
	JButton btnLuu, btnXoa, btnThoat;
	
	ArrayList<PhongBan> dsPhongBan;
	ArrayList<NhanVien> dsNhanVienPhongBan;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
	
	PhongBan pbSelected = null;
	NhanVien nvSelected = null;
	public QuanLyNhanVienUI(String title){
		super(title);
		addControls();
		addEvents();
		fakeData();
	}

	public void addEvents() {
		// TODO Auto-generated method stub
		cboPhongBan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cboPhongBan.getSelectedIndex()==-1)
					return ;
				pbSelected = (PhongBan) cboPhongBan.getSelectedItem();
				listNhanVien.setListData(pbSelected.getNhanviens());
				
			}
		});
		
		listNhanVien.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(listNhanVien.getSelectedIndex()==-1) return;
				nvSelected = listNhanVien.getSelectedValue();
				txtMa.setText(nvSelected.getMaNhanVien());
				txtTen.setText(nvSelected.getTenNhanVien());
				
				txtNamSinh.setText(sdf.format(nvSelected.getNamSinh()));
				txtNgayVaoLam.setText(sdf.format(nvSelected.getNgayVaoLamViec()));
				
			}
		});
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyLuu();
			}
		});
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyXoa();
			}
		});
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
	}

	protected void xuLyXoa() {
		// TODO Auto-generated method stub
		if(nvSelected!=null){
			pbSelected.getNhanviens().remove(nvSelected);
			nvSelected = null;
			listNhanVien.setListData(pbSelected.getNhanviens());
		}
	}

	protected void xuLyLuu() {
		// TODO Auto-generated method stub
		try{
			NhanVien nv = new NhanVien(
					txtMa.getText(),
					txtTen.getText(),
					sdf.parse(txtNgayVaoLam.getText()), 
					sdf.parse(txtNamSinh.getText()));
			if(pbSelected!=null){
				pbSelected.themNhanVien(nv);
				listNhanVien.setListData(pbSelected.getNhanviens());
			}
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}

	public void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);
		
		JPanel pnPhongBan = new JPanel();
		pnPhongBan.setLayout(new FlowLayout());
		JLabel lblPhongBan = new JLabel("Chọn phòng ban");
		cboPhongBan = new JComboBox<PhongBan>();
		cboPhongBan.setPreferredSize(new Dimension(200, 25));
		pnPhongBan.add(lblPhongBan);
		pnPhongBan.add(cboPhongBan);
		pnMain.add(pnPhongBan);
		
		JPanel pnDanhSachVaChiTiet = new JPanel();
		pnDanhSachVaChiTiet.setLayout(new GridLayout(1, 2));
		pnMain.add(pnDanhSachVaChiTiet);
		JPanel pnDanhSach = new JPanel();
		pnDanhSach.setLayout(new BorderLayout());
		listNhanVien = new JList<NhanVien>();
		JScrollPane sc = new JScrollPane(listNhanVien, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnDanhSach.add(sc, BorderLayout.CENTER);
		pnDanhSachVaChiTiet.add(pnDanhSach);
		
		Border borderDanhSach = BorderFactory.createLineBorder(Color.blue);
		TitledBorder titledBorder = new TitledBorder(borderDanhSach, "Danh sách");
		titledBorder.setTitleColor(Color.red);
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		pnDanhSach.setBorder(titledBorder);
				
		
		JPanel pnChiTiet = new JPanel();
		pnChiTiet.setLayout(new BoxLayout(pnChiTiet, BoxLayout.Y_AXIS));
		pnDanhSachVaChiTiet.add(pnChiTiet);
		
		Border borderChiTiet = BorderFactory.createLineBorder(Color.blue);
		TitledBorder titledBorder2 = new TitledBorder(borderChiTiet,"Chi tiết");
		titledBorder2.setTitleColor(Color.CYAN);
		titledBorder2.setTitleJustification(TitledBorder.CENTER);
		pnChiTiet.setBorder(titledBorder2);
				
		
		JPanel pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout());
		JLabel lblMa = new JLabel("Mã: ");
		txtMa = new JTextField(15);
		pnMa.add(lblMa);
		pnMa.add(txtMa);
		pnChiTiet.add(pnMa);
		
		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout());
		JLabel lblTen = new JLabel("Tên:");
		txtTen = new JTextField(15);
		pnTen.add(lblTen);
		pnTen.add(txtTen);
		pnChiTiet.add(pnTen);
		
		JPanel pnNgayVao = new JPanel();
		pnNgayVao.setLayout(new FlowLayout());
		JLabel lblNgayVao = new JLabel("Ngày vào: ");
		txtNgayVaoLam = new JTextField(15);
		pnNgayVao.add(lblNgayVao);
		pnNgayVao.add(txtNgayVaoLam);
		pnChiTiet.add(pnNgayVao);
		
		JPanel pnNgaySinh = new JPanel();
		pnNgaySinh.setLayout(new FlowLayout());
		JLabel lblNgaySinh = new JLabel("Ngày sinh: ");
		txtNamSinh = new JTextField(15);
		pnNgaySinh.add(lblNgaySinh);
		pnNgaySinh.add(txtNamSinh);
		pnChiTiet.add(pnNgaySinh);
		
		lblMa.setPreferredSize(lblNgaySinh.getPreferredSize());
		lblTen.setPreferredSize(lblNgaySinh.getPreferredSize());
		lblNgayVao.setPreferredSize(lblNgaySinh.getPreferredSize());
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnLuu = new JButton("Lưu");
		btnXoa = new JButton("Xóa");
		btnThoat = new JButton("Thoát");
		pnButton.add(btnLuu);
		pnButton.add(btnXoa);
		pnButton.add(btnThoat);
		pnMain.add(pnButton);
		
		Border borderButton = BorderFactory.createLineBorder(Color.DARK_GRAY);
		TitledBorder titleBorderButton = new 
				TitledBorder(borderButton,"Chọn chức năng");
		titleBorderButton.setTitleColor(Color.RED);
		titleBorderButton.setTitleJustification(TitledBorder.CENTER);
		pnButton.setBorder(titleBorderButton);
		
	}
	
	public void showWindow(){
		this.setSize(550, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	@SuppressWarnings("deprecation")
	public void fakeData(){
		dsPhongBan = new ArrayList<>();
		
		PhongBan phtgv = new PhongBan();
		phtgv.setMaPhongBan("p1");
		phtgv.setTenPhongBan("Phòng hợp tác giảng viên");
		
		PhongBan pkd = new PhongBan();
		pkd.setMaPhongBan("p2");
		pkd.setTenPhongBan("Phòng kinh doanh");
		
		PhongBan pkt = new PhongBan();
		pkt.setMaPhongBan("p3");
		pkt.setTenPhongBan("Phòng kế toán");
		
		dsPhongBan.add(phtgv);
		dsPhongBan.add(pkd);
		dsPhongBan.add(pkt);
		
		phtgv.themNhanVien(new NhanVien("NV01", "Nguyễn Thị Lúa", 
				new Date(2016-1900, 1,1), new Date(1990-1900, 1,1)));
		phtgv.themNhanVien(new NhanVien("NV02", "Nguyễn Thị Bình", 
				new Date(2015-1900, 1,1), new Date(1989-1900, 1,1)));
		pkd.themNhanVien(new NhanVien("NV04", "Trần Thị Thoát", 
				new Date(2013-1900, 1,1), new Date(1985-1900, 1,1)));
		pkd.themNhanVien(new NhanVien("NV03", "Trần Thị Minh Hăng", 
				new Date(2014-1900, 1,1), new Date(1977-1900, 1,1)));
		pkt.themNhanVien(new NhanVien("NV05", "Nguyễn Trí Nhân", 
				new Date(2005-1900, 1,1), new Date(1970-1900, 1,1)));
		
		for(PhongBan pb: dsPhongBan){
			cboPhongBan.addItem(pb);
		}
		
	}

}
