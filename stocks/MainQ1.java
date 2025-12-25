//Noa Levi-214506396, Hila Ivgi- 326138377
//Main separate to sections 1 in question 
package stocks;

public class MainQ1 {

	 public static void main(String[] args) {
		 
		 StockServer server = new StockServer();
	

		 new Thread(new StockReaderThread("Tami Tan", server, StockServer.Stock.MICROSOFT)).start();
		 new Thread(new StockReaderThread("Tim Sroly", server, StockServer.Stock.APPLE)).start();
		 new Thread(new StockReaderThread("Sima Dides", server, StockServer.Stock.GOOGLE)).start();

	 }
}