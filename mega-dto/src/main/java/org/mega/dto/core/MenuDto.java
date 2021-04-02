package org.mega.dto.core;

import java.io.Serializable;
import java.util.List;

import org.mega.dao.entity.RoleMenu;

public class MenuDto implements Serializable {
	private static final long serialVersionUID = -3225963681713671977L;
	private List<RoleMenu> mainMenu;
	private Long mainSelected;
	private List<RoleMenu> subMenu;
	private Long menuSelected;

	public MenuDto() {
		// mainMenu = new ArrayList<RoleMenu>();
		//
		// RoleMenu messageMenu = createMenu(1L, 1, 1L, 1L, "Tin nhắn", "", 0);
		// mainMenu.add(messageMenu);
		//
		// RoleMenu invoiceMenu = createMenu(2L, 1, 2L, 2L, "Quản lý công việc",
		// "", 0);
		// mainMenu.add(invoiceMenu);
		//
		// RoleMenu officeMenu = createMenu(3L, 1, 3L, 3L, "Văn phòng", "", 0);
		// mainMenu.add(officeMenu);
		//
		// RoleMenu salesMenu = createMenu(4L, 1, 4L, 4L, "Kinh doanh", "", 0);
		// mainMenu.add(salesMenu);
		//
		// RoleMenu serviceMenu = createMenu(5L, 1, 5L, 5L, "Dịch vụ", "", 0);
		// mainMenu.add(serviceMenu);
		//
		// RoleMenu poMenu = createMenu(6L, 1, 6L, 6L, "Mua hàng", "", 0);
		// mainMenu.add(poMenu);
		//
		// RoleMenu intentMenu = createMenu(7L, 1, 7L, 7L, "Quản lý kho", "",
		// 0);
		// mainMenu.add(intentMenu);
		//
		// RoleMenu accountMenu = createMenu(8L, 1, 8L, 8L, "Kế toán", "", 0);
		// mainMenu.add(accountMenu);
		//
		// RoleMenu hrMenu = createMenu(9L, 1, 9L, 9L, "Nhân sự", "", 0);
		// mainMenu.add(hrMenu);
		//
		// RoleMenu adminMenu = createMenu(10L, 1, 10L, 10L, "Quản lý", "", 0);
		// mainMenu.add(adminMenu);
		//
		// RoleMenu commonMenu = createMenu(11L, 1, 11L, 11L, "Thông tin chung",
		// "", 0);
		// mainMenu.add(commonMenu);
		//
		//
		// List<RoleMenu> subMessageMenu = new ArrayList<RoleMenu>();
		// RoleMenu subMessageMenu1 = createMenu(201L, 0, 1L, 201L, "Nhân viên",
		// "", 1);
		// subMessageMenu.add(subMessageMenu1);
		// RoleMenu subMessageMenu2 = createMenu(202L, 0, 1L, 202L, "Hộp tin",
		// "msg010", 0);
		// subMessageMenu.add(subMessageMenu2);
		// RoleMenu subMessageMenu3 = createMenu(203L, 0, 1L, 203L, "Gửi mail",
		// "msg020", 0);
		// subMessageMenu.add(subMessageMenu3);
		// RoleMenu subMessageMenu4 = createMenu(204L, 0, 1L, 204L,
		// "Danh sách thành viên", "msg030", 0);
		// subMessageMenu.add(subMessageMenu4);
		// RoleMenu subMessageMenu5 = createMenu(205L, 0, 1L, 205L, "Lịch", "",
		// 1);
		// subMessageMenu.add(subMessageMenu5);
		// RoleMenu subMessageMenu6 = createMenu(206L, 0, 1L, 206L, "Nhắc việc",
		// "msg040", 0);
		// subMessageMenu.add(subMessageMenu6);
		// RoleMenu subMessageMenu7 = createMenu(207L, 0, 1L, 207L,
		// "Lịch cá nhân", "msg050", 0);
		// subMessageMenu.add(subMessageMenu7);
		// messageMenu.setSubMenu(subMessageMenu);
		//
		//
		// List<RoleMenu> subInvoiceMenu = new ArrayList<RoleMenu>();
		// RoleMenu subInvoiceMenu1 = createMenu(301L, 0, 2L, 301L,
		// "Khách hàng", "", 1);
		// subInvoiceMenu.add(subInvoiceMenu1);
		// RoleMenu subInvoiceMenu2 = createMenu(302L, 0, 2L, 302L,
		// "Khách hàng", "ive010", 0);
		// subInvoiceMenu.add(subInvoiceMenu2);
		// RoleMenu subInvoiceMenu3 = createMenu(303L, 0, 2L, 303L,
		// "Chứng từ nội địa", "", 1);
		// subInvoiceMenu.add(subInvoiceMenu3);
		// RoleMenu subInvoiceMenu4 = createMenu(304L, 0, 2L, 304L, "Hợp đồng",
		// "ive020", 0);
		// subInvoiceMenu.add(subInvoiceMenu4);
		// RoleMenu subInvoiceMenu5 = createMenu(305L, 0, 2L, 305L,
		// "Biên bản bàn giao", "ive030", 0);
		// subInvoiceMenu.add(subInvoiceMenu5);
		// RoleMenu subInvoiceMenu6 = createMenu(306L, 0, 2L, 306L,
		// "Thanh lý hợp đồng", "ive040", 0);
		// subInvoiceMenu.add(subInvoiceMenu6);
		// RoleMenu subInvoiceMenu7 = createMenu(307L, 0, 2L, 307L,
		// "Thông báo giao hàng", "ive050", 0);
		// subInvoiceMenu.add(subInvoiceMenu7);
		// RoleMenu subInvoiceMenu8 = createMenu(308L, 0, 2L, 308L,
		// "Ngoài thương", "", 1);
		// subInvoiceMenu.add(subInvoiceMenu8);
		// RoleMenu subInvoiceMenu9 = createMenu(309L, 0, 2L, 309L,
		// "Packing list", "ive060", 1);
		// subInvoiceMenu.add(subInvoiceMenu9);
		// RoleMenu subInvoiceMenu10 = createMenu(310L, 0, 2L, 310L,
		// "Delivery notification", "ive070", 0);
		// subInvoiceMenu.add(subInvoiceMenu10);
		// RoleMenu subInvoiceMenu11 = createMenu(311L, 0, 2L, 311L, "Biểu mẫu",
		// "", 1);
		// subInvoiceMenu.add(subInvoiceMenu11);
		// RoleMenu subInvoiceMenu12 = createMenu(1312L, 0, 2L, 312L,
		// "Mẫu hợp đồng", "ive080", 0);
		// subInvoiceMenu.add(subInvoiceMenu12);
		// RoleMenu subInvoiceMenu13 = createMenu(313L, 0, 2L, 313L,
		// "Mẫu biên bản bàn giao", "ive090", 0);
		// subInvoiceMenu.add(subInvoiceMenu13);
		// RoleMenu subInvoiceMenu14 = createMenu(314L, 0, 2L, 314L,
		// "Mẫu thanh lý", "ive100", 0);
		// subInvoiceMenu.add(subInvoiceMenu14);
		// invoiceMenu.setSubMenu(subInvoiceMenu);
		//
		//
		// List<RoleMenu> subSalesMenu = new ArrayList<RoleMenu>();
		// RoleMenu subSalesMenu1 = createMenu(101L, 0, 4L, 101L, "Kinh doanh",
		// "", 1);
		// subSalesMenu.add(subSalesMenu1);
		// RoleMenu subSalesMenu2 = createMenu(102L, 0, 4L, 102L, "Khách hàng",
		// "sel010", 0);
		// subSalesMenu.add(subSalesMenu2);
		// RoleMenu subSalesMenu3 = createMenu(103L, 0, 4L, 103L, "Tiềm năng",
		// "sel020", 0);
		// subSalesMenu.add(subSalesMenu3);
		// RoleMenu subSalesMenu4 = createMenu(104L, 0, 4L, 104L, "Cơ hội",
		// "sel030", 0);
		// subSalesMenu.add(subSalesMenu4);
		// RoleMenu subSalesMenu5 = createMenu(105L, 0, 4L, 105L, "Báo giá",
		// "sel040", 0);
		// subSalesMenu.add(subSalesMenu5);
		// RoleMenu subSalesMenu6 = createMenu(106L, 0, 4L, 106L, "Hợp đồng",
		// "sel050", 0);
		// subSalesMenu.add(subSalesMenu6);
		// RoleMenu subSalesMenu7 = createMenu(107L, 0, 4L, 107L,
		// "Yêu cầu xuất hàng", "sel060", 0);
		// subSalesMenu.add(subSalesMenu7);
		// RoleMenu subSalesMenu8 = createMenu(108L, 0, 4L, 108L,
		// "Thông báo giao hàng", "sel070", 0);
		// subSalesMenu.add(subSalesMenu8);
		// RoleMenu subSalesMenu9 = createMenu(109L, 0, 4L, 109L, "Hóa đơn", "",
		// 1);
		// subSalesMenu.add(subSalesMenu9);
		// RoleMenu subSalesMenu10 = createMenu(110L, 0, 4L, 110L,
		// "Báo cáo bán hàng", "sel080", 0);
		// subSalesMenu.add(subSalesMenu10);
		// RoleMenu subSalesMenu11 = createMenu(111L, 0, 4L, 111L,
		// "Hóa đơn bán hàng", "sel090", 0);
		// subSalesMenu.add(subSalesMenu11);
		// RoleMenu subSalesMenu12 = createMenu(112L, 0, 4L, 112L, "Sản phẩm",
		// "", 1);
		// subSalesMenu.add(subSalesMenu12);
		// RoleMenu subSalesMenu13 = createMenu(113L, 0, 4L, 113L, "Sản phẩm",
		// "sel100", 0);
		// subSalesMenu.add(subSalesMenu13);
		// RoleMenu subSalesMenu14 = createMenu(114L, 0, 4L, 114L, "Dịch vụ",
		// "sel110", 0);
		// subSalesMenu.add(subSalesMenu14);
		// RoleMenu subSalesMenu15 = createMenu(115L, 0, 4L, 115L, "Đặt hàng",
		// "", 1);
		// subSalesMenu.add(subSalesMenu15);
		// RoleMenu subSalesMenu16 = createMenu(116L, 0, 4L, 116L,
		// "Yêu cầu đặt hàng", "sel120", 0);
		// subSalesMenu.add(subSalesMenu16);
		// RoleMenu subSalesMenu17 = createMenu(117L, 0, 4L, 117L, "Biểu mẫu",
		// "sel", 1);
		// subSalesMenu.add(subSalesMenu17);
		// RoleMenu subSalesMenu18 = createMenu(118L, 0, 4L, 118L,
		// "Mẫu báo giá bán", "sel130", 0);
		// subSalesMenu.add(subSalesMenu18);
		// salesMenu.setSubMenu(subSalesMenu);
		//
		//
		// subMenu = subSalesMenu;
		//
		// mainSelected = 4L;
		// menuSelected = 103L;
	}

	// private RoleMenu createMenu(Long id, Integer main, Long mainId, Long
	// idMenu, String name, String pageCode, Integer group) {
	// Menu menu = new Menu();
	// menu.setId(idMenu);
	// menu.setName(name);
	// //menu.setPageCode(pageCode);
	// menu.setMenuGroup(group > 0);
	//
	// RoleMenu roleMenu = new RoleMenu();
	// roleMenu.setId(idMenu);
	// roleMenu.setMain(main);
	// roleMenu.setMainId(mainId);
	// roleMenu.setMenu(menu);
	// return roleMenu;
	// }

	public List<RoleMenu> getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(List<RoleMenu> mainMenu) {
		this.mainMenu = mainMenu;
	}

	public Long getMainSelected() {
		return mainSelected;
	}

	public void setMainSelected(Long mainSelected) {
		this.mainSelected = mainSelected;
	}

	public List<RoleMenu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<RoleMenu> subMenu) {
		this.subMenu = subMenu;
	}

	public Long getMenuSelected() {
		return menuSelected;
	}

	public void setMenuSelected(Long menuSelected) {
		this.menuSelected = menuSelected;
	}
}
