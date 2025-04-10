#include <SPI.h>
#include <MFRC522.h>
String read_id;
MFRC522 rfid(/*SS_PIN*/ 5, /*RST_PIN*/ 22);
String mfrc522_readID()
{
  String ret;
  if (rfid.PICC_IsNewCardPresent() && rfid.PICC_ReadCardSerial())  
   //讀取並確認不是重複卡片
  {
    MFRC522::PICC_Type piccType = rfid.PICC_GetType(rfid.uid.sak);
    for (byte i = 0; i < rfid.uid.size; i++) {
      ret += (rfid.uid.uidByte[i] < 0x10 ? "0" : "");
      ret += String(rfid.uid.uidByte[i], HEX);
    }//轉成16進制儲存
  }
  rfid.PICC_HaltA();
  // Stop encryption on PCD
  rfid.PCD_StopCrypto1();
  return ret;//回傳已完成轉存之id
}
void setup()
{
  SPI.begin();
  rfid.PCD_Init();
  Serial.begin(115200);
}
void loop()
{
  read_id = mfrc522_readID(); //呼叫函式取得16進制id
  if (read_id != "") {
    Serial.print("偵測到 RFID: ");
    Serial.println(read_id); 
  }
  delay(1000);
}