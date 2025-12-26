//Noa Levi-214506396, Hila Ivgi- 326138377
//Main separate into sections in question 3 to 5
//https://github.com/noapartosh01/Assignment2
package stocks;

public class MainQ3 {

		public static void main(String[] args) {
			StockServer server = new StockServer();

			new Thread(new StockReaderThread("Tami Tan", server, StockServer.Stock.MICROSOFT)).start();
			new Thread(new StockReaderThread("Tim Sroly", server, StockServer.Stock.APPLE)).start();
			new Thread(new StockReaderThread("Sima Dides", server, StockServer.Stock.GOOGLE)).start();

			new StockUpdateThread(server).start();
		}
	}