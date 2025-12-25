//Noa Levi-214506396, Hila Ivgi- 326138377
package stocks;

import stocks.StockServer.Stock;
import java.lang.reflect.Field;

public class StockUpdateThread extends Thread {
	private StockServer stockServer;

	public StockUpdateThread(StockServer stockServer) {
		this.stockServer = stockServer;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			synchronized(stockServer) {
				updateStockValue(Stock.MICROSOFT, getRandomValue());
				updateStockValue(Stock.APPLE, getRandomValue());
				updateStockValue(Stock.GOOGLE, getRandomValue());
			}

			System.out.println("Stock values updated!");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateStockValue(Stock stock, int value) {
		try {
			String fieldName = getFieldName(stock);
			Field field = stockServer.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(stockServer, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getFieldName(Stock stock) {
		switch(stock) {
			case MICROSOFT:
				return "microsoftValue";
			case APPLE:
				return "appleValue";
			case GOOGLE:
				return "googleValue";
			default:
				throw new IllegalArgumentException("Unknown stock type");
		}
	}

	private int getRandomValue() {
		return 100 + (int)(Math.random() * 401);
	}
}
