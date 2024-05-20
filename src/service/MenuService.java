package service;

import java.util.List;
import java.util.Map;

import dao.MenuDao;

public class MenuService {
   private static MenuService instance;

   private MenuService() {

   }

   public static MenuService getInstance() {
      if (instance == null) {
         instance = new MenuService();
      }
      return instance;
   }
   
   MenuDao menuDao = MenuDao.getInstance();
   
   public void menuInsert(List<Object> param) {
      menuDao.menuinsert(param);
   }

   public void menuUpdate(List<Object> param, int sel) {
      menuDao.menuUpdate(param, sel);
   }

   public List<Map<String, Object>> menuList() {
      return menuDao.menuList();
   }

   public int menuDelete(List<Object> param) {
	   return menuDao.menuDelete(param);
   }

   public List<Map<String, Object>> menuInformation(List<Object> param) {
	   return menuDao.menuInformation(param);
   }

   public List<Map<String, Object>> detail(List<Object> param) {
		return menuDao.detail(param);
	}

   public List<Map<String, Object>> menuPaging(List<Object> param) {
		return menuDao.menuPaging(param);
	}
}