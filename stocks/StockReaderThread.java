//Noa Levi-214506396, Hila Ivgi- 326138377

package stocks;
public class StockReaderThread extends Thread {
	private String name;
	private StockServer stockServer;
	private StockServer.Stock stockType;

	public StockReaderThread(String name, StockServer stockServer, StockServer.Stock stockType) {
	    this.name = name;
	    this.stockServer = stockServer;
	    this.stockType = stockType;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			int value;
			synchronized(stockServer) {
				value = stockServer.GetStock(stockType);
			}
			System.out.println("Name: " + name + ", " + stockType + " Stock: " + value + " USD");

			try {
				long sleepTime = 1000 + (long)(Math.random() * 2000);
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}