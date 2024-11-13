package org.example;
/*
例行賽的時候非假日票價 500 元，假日 600 元，如果是會員則打八折，但如果是買套票則一律為 250 元。
季後賽票價 1000 元，會員打八折但沒有套票，也不分是否為例假日。
 */
public class cal {
    public static  int calprice(int isHoliday, int isMember, int isPlayoff, int isPackageTicket) throws Exception{
        int price;
        if(isPlayoff==1){
            if(isMember==1){
                return 800;
            }else if(isMember==0){
                return 1000;
            }
            throw new Exception("格式錯誤");
        }else if(isPlayoff==0){
            if(isPackageTicket==0) {
                if (isHoliday == 1) {//600
                    if(isMember==1){
                        return 480;
                    }else if(isMember==0){
                        return 600;
                    }
                    throw new Exception("格式錯誤");
                } else if (isHoliday == 0) {//500
                    if(isMember==1){
                        return 400;
                    }else if(isMember==0){
                        return 500;
                    }
                    throw new Exception("格式錯誤");
                }
                throw new Exception("格式錯誤");
            }else if(isPackageTicket==1){
                return  250;
            }
            throw new Exception("格式錯誤");
        }
        throw new Exception("格式錯誤");
    }
}
