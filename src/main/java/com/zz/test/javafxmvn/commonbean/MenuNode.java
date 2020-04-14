package com.zz.test.javafxmvn.commonbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.zz.test.javafxmvn.commontool.RegexpTool;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@Component
public class MenuNode {
	//private Map<String, Object> fields;
	private String parentID = "-1";
	private String menuId = "0";
	private String menuName = "0";
	
	private String orderById = "";//暂不实现
	private String remark = "";
	private String icon = ""; //icon图形
	private String style = "";//风格，暂不实现
	private String show = "1";//是否展示，
	private boolean expanded = true;//根节点是否展开
	
	/**
	 * 菜单字段的格式，其中必须有数字，如菜单id：f_f_2_menuId,2表示是第二层菜单
	 */
	public static final String menuFieldReg = "^[a-zA-Z_]+_\\d+_[a-zA-Z_]+";
	
	public static final String menuFieldRegLeft = "^[a-zA-Z_]+_"; 
	
	/**
	 * 默认菜单字段的格式
	 */
	public static final String menuFieldRegDefalt = "f_f_";
	
	
	//子菜单
	private List<MenuNode> menuSub = new ArrayList<MenuNode>();
	
	
	public  MenuNode() {
		
	}
	
	public  MenuNode(String parentID, String menuId, String menuName, boolean expanded, String icon) {
		this.parentID = parentID;
		this.menuId = menuId;
		this.menuName = menuName;
		this.expanded = expanded;
		this.icon = icon;
		
	}
	
	
	
	public MenuNode(String parentID, String menuId, String menuName, String orderById, String remark, String icon,
			String style, List<MenuNode> menuSub) {
		super();
		this.parentID = parentID;
		this.menuId = menuId;
		this.menuName = menuName;
		this.orderById = orderById;
		this.remark = remark;
		this.icon = icon;
		this.style = style;
		this.menuSub = menuSub;
	}
	
	public MenuNode(String parentID, String menuId, String menuName, String orderById, String remark, String icon,
			String style, String show, boolean expanded) {
		super();
		this.parentID = parentID;
		this.menuId = menuId;
		this.menuName = menuName;
		this.orderById = orderById;
		this.remark = remark;
		this.icon = icon;
		this.style = style;
		this.show = show;
		this.expanded = expanded;
	}


	/**
	 * Desc:通过list获取菜单类MenuNode
	 * @author jld.zhangzhou
	 * @datetime 2020-03-31 16:05:16
	 * @modify_record:
	 * @param muneList
	 * @param arg arg[0] is pattern '[a-zA-Z_]+_' ,like 'ab_c_d_c_ ',so, the field of list's row must be like 'ab_c_d_c_2_menuId' 
	 * that means menu is of 2th level,and the field's value is related to menuId of class MenuNode;
	 * @return
	 * @throws Exception
	 */
	public static MenuNode getMenu(List<Map<String, Object>> muneList,MenuNode rootMenuNode, String... arg) throws Exception {
		MenuNode rootMenu = rootMenuNode;
		String menuStartWith = menuFieldRegDefalt;
		
		if(arg.length > 0) {//针对arg有参数，
			menuStartWith = arg[0];
			if(!RegexpTool.isMatch(menuStartWith, menuFieldRegLeft)) {
				throw new Exception(String.format("arg[0] pattern is not '%s'", menuFieldRegLeft) );
			}
		}
		
		/**
		 * 1、获取菜单层次
		 */
		
		int maxLevel = 0;//菜单最大层数
		boolean f_1th_map = true;//第一次遍历muneList标记
		for(Map<String, Object> map : muneList) {//遍历数据行（f1_*,f1_*,f2_*,f2_*格式的数据）（后改成f_f_1_*）
			
			if(f_1th_map) {//第一次遍历记录maxLevel
				
				for(Entry<String, Object> entry : map.entrySet()) {
					if(!RegexpTool.isMatch(entry.getKey(), menuFieldRegLeft + ".*")) {
						continue;
					}else {
						if(!RegexpTool.isMatch(entry.getKey(), menuFieldReg)) {
							throw new Exception(String.format("muneList field about mune %s pattern is not '%s'", entry.getKey(), menuFieldReg));
						}
					}
					String levelStr = RegexpTool.getOneByReg(entry.getKey(), "(?<="+menuStartWith+")\\d+");
					int level = Integer.parseInt(levelStr == null ? "0" : levelStr);//获取当前菜单层级。

					if(maxLevel < level) {
						maxLevel = level;
					}
				}
				f_1th_map = false;
			}
			
			int i = 0;
			List<MenuNode> enuNodeList_1 = new ArrayList<>();
			MenuNode menuNode = null;
			String parentId = "";
			while(maxLevel > i) {//依次处理 f1,f2,f3,f4...........，该行各个层级的数据，放入rootMenu 对应的位置。
				if(i == 0) {
					enuNodeList_1 = rootMenu.getMenuSub();
				}else {
					enuNodeList_1 = menuNode.getMenuSub();
				}
				
				/**
				 * 获取map值。
				 */
				String parentID = (String) map.get(String.format("%s%d_%s", menuStartWith,i+1,"parentID"));
				String menuId = (String) map.get(String.format("%s%d_%s", menuStartWith,i+1,"menuId"));
				String menuName = (String) map.get(String.format("%s%d_%s", menuStartWith,i+1,"menuName"));
				String orderById = (String) map.get(String.format("%s%d_%s", menuStartWith,i+1,"orderById"));
				String remark = (String) map.get(String.format("%s%d_%s", menuStartWith,i+1,"remark"));
				String icon = (String) map.get(String.format("%s%d_%s", menuStartWith,i+1,"icon"));
				String style = (String) map.get(String.format("%s%d_%s", menuStartWith,i+1,"style"));
				String show = (String) map.get(String.format("%s%d_%s", menuStartWith,i+1,"show"));
				boolean expanded = (boolean) (map.get(String.format("%s%d_%s", menuStartWith,i+1,"expanded")) == null ? false :map.get(String.format("%s%d_%s", menuStartWith,i+1,"expanded")));
				if(menuId == null) {
					break;
				}
				if(enuNodeList_1.size() == 0) {
					menuNode = new MenuNode(parentID == null ? "-1" : parentID, menuId == null ? "0" : menuId, menuName == null ? "0" : menuName, orderById == null ? "" : orderById,
							remark == null ? "" : remark, icon == null ? "" : icon, style == null ? "" : style, StringUtils.isBlank(show) ? "1" : show, expanded);
					enuNodeList_1.add(menuNode);
				}else {
					//如果size>0，且相等于当前，则menuNode重新指向；如果size=0且不相等，则新建。
					boolean f_sizem0e = false;//flag
					for(MenuNode m : enuNodeList_1) {
						if(m.getMenuId().equals(menuId)) {
							menuNode = m;
							f_sizem0e = true;
							break;
						}
					}
					
					if(!f_sizem0e) {
						menuNode = new MenuNode(parentID == null ? "-1" : parentID, menuId == null ? "0" : menuId, menuName == null ? "0" : menuName, orderById == null ? "" : orderById,
								remark == null ? "" : remark, icon == null ? "" : icon, style == null ? "" : style, StringUtils.isBlank(show) ? "1" : show, expanded);
						enuNodeList_1.add(menuNode);
					}
					
				}
				
				enuNodeList_1 = menuNode.getMenuSub();//重新指向下一个menuNode
				i++;
			}
		}
		return rootMenu;
	}
	
	public static MenuNode getMenu(List<Map<String, Object>> muneList, String... arg) throws Exception {
		return MenuNode.getMenu(muneList, new MenuNode(), arg);

		
	}
	
	/**
	 * Desc:get treeview by MenuNode
	 * @author jld.zhangzhou
	 * @datetime 2020-04-13 11:03:01
	 * @modify_record:
	 * @param menuNode
	 * @return
	 */
	public static TreeView<String> getMenuTreeview(MenuNode menuNode) {
		if(menuNode == null) {
			return null;
		}
		
		TreeItem<String> rootNode =  MenuNode.TreeItemZ(menuNode.getMenuName(), menuNode.getIcon());
		rootNode.setExpanded(menuNode.getExpanded());

		
		MenuNode.setMenuNodeToTreeItem(rootNode, menuNode);
		TreeView<String> tree = new TreeView<> (rootNode);
		
		
		return tree;
	}
	
	/**
	 * Desc:get treeview by muneList
	 * @author jld.zhangzhou
	 * @datetime 2020-04-13 16:53:39
	 * @modify_record:
	 * @param muneList
	 * @return
	 * @throws Exception
	 */
	public static TreeView<String> getMenuTreeview(List<Map<String, Object>> muneList) throws Exception {
		return MenuNode.getMenuTreeview(MenuNode.getMenu(muneList));
	}
	
	/**
	 * Desc:递归通过TreeItem，menuNode赋值构建item
	 * @author jld.zhangzhou
	 * @datetime 2020-04-13 14:41:26
	 * @modify_record:
	 * @param rootNode
	 * @param menuNode
	 */
	private static void setMenuNodeToTreeItem(TreeItem<String> rootNode, MenuNode menuNode) {
		/*TreeItem<String> treeItem = rootNode;//《1》
		MenuNode menu = menuNode;//《2》
		if(menuNode.getMenuSub().size() == 0) {
			//treeItem = treeItem.getParent();//递归回溯-zz20200413，此处无需回溯。又不是全局变量引用
			return;
		}
		int i = 0;
		for(MenuNode m : menu.getMenuSub()) {
			treeItem.getChildren().add(MenuNode.TreeItemZ(m.getMenuName(), m.getIcon()));
			MenuNode.setMenuNodeToTreeItem(treeItem.getChildren().get(i), m);
			i++;
		}*/
		
		if(menuNode.getMenuSub().size() == 0) {
			//rootNode = rootNode.getParent();//递归回溯-zz20200413，注意，在java中，并没有该情况，除非全局变量定义。java中在进入方法时候，
			//传入的引用，在该方法闭合代码内（即便方法中调用自身-递归），该引用在此次方法中没有重新指向时候，即便像《1》，《2》写法，临时赋值，也不会让引用重新指向递归内的结果。
			//总结：即不用担心在递归中，方法中的引用被重新指向。即上述注释部分一样正确。
			return;
		}
		int i = 0;
		for(MenuNode m : menuNode.getMenuSub()) {
			TreeItem<String> item = MenuNode.TreeItemZ(m.getMenuName(), m.getIcon());
			item.setExpanded(m.getExpanded());
			rootNode.getChildren().add(item);
			MenuNode.setMenuNodeToTreeItem(rootNode.getChildren().get(i), m);
			i++;
		}
	}
	
	/**
	 * Desc:获取TreeItem
	 * @author jld.zhangzhou
	 * @datetime 2020-04-13 10:58:36
	 * @modify_record:
	 * @param value
	 * @param graphic
	 * @return
	 */
	private static TreeItem<String> TreeItemZ (final String value, final String graphic) {
		TreeItem<String> i = null;
		if(StringUtils.isBlank(graphic)) {
			i = new TreeItem<String>(value);
		}else {
			//Node rootIcon = new ImageView(new Image(getClass().getResourceAsStream("/img/treeview/treeview_main/next-mark-right_1.png")));
			Node icon = new ImageView(new Image(graphic));
			i = new TreeItem<String>(value, icon);
		}
		return i;
	}
	
	public List<MenuNode> getMenuSub() {
		return menuSub;
	}
	public void setMenuSub(List<MenuNode> menuSub) {
		this.menuSub = menuSub;
	}




	public String getParentID() {
		return parentID;
	}




	public void setParentID(String parentID) {
		this.parentID = parentID;
	}




	public String getMenuId() {
		return menuId;
	}




	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}




	public String getMenuName() {
		return menuName;
	}




	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}




	public String getOrderById() {
		return orderById;
	}




	public void setOrderById(String orderById) {
		this.orderById = orderById;
	}




	public String getRemark() {
		return remark;
	}




	public void setRemark(String remark) {
		this.remark = remark;
	}




	public String getIcon() {
		return icon;
	}




	public void setIcon(String icon) {
		this.icon = icon;
	}




	public String getStyle() {
		return style;
	}




	public void setStyle(String style) {
		this.style = style;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	

	
	
	
}
