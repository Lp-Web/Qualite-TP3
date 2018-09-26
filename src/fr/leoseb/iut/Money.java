package fr.leoseb.iut;

public class Money {
	
	private double amount;
	private String currency;

	public Money(double amount, String currency) {
		if((amount < 0) || (currency == null)) {
			throw new IllegalArgumentException(); 
		}
		
		this.amount = amount;
		this.currency = currency;
		
	}

	public double getAmount() {
		return this.amount;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void add(Money m) {
		this.add(m.getAmount(), m.getCurrency());
	}

	public void add(double namount, String ncurrency) {
		if(currency.equals("EUR")) {
			if(ncurrency.equals("USD")) {
				this.amount += namount*1.29;
			} else {
				this.amount += namount;
			}
		}else {
			if(ncurrency.equals("EUR")) {
				this.amount += namount/1.29;
			}else {
				this.amount += namount;
			}
		}
	}
}
