package service;

import java.util.List;
import java.util.Map;

import dao.ReserveDao;

public class ReserveService {
   private static ReserveService instance;

   private ReserveService() {

   }

   public static ReserveService getInstance() {
      if (instance == null) {
         instance = new ReserveService();
      }
      return instance;
   }
   
   ReserveDao reserveDao = ReserveDao.getInstance();
   
//   공지사항 출력
   public List<Map<String, Object>> noticeList() {
      return reserveDao.noticeList();
   }

   
   public void reserveInsert(List<Object> param){
      reserveDao.reservation(param);
   }
   
   public boolean reservationSeat(){
      List<Map<String, Object>> status = reserveDao.reservationSeat(); 
      if(status == null) {
         return false;
      }
      return true;
   }

   public List<Map<String, Object>> reservation() {
      return reserveDao.reserveList();
   }
   
   public List<Map<String, Object>> resNo() {
	   return reserveDao.resNo();
   }
}