package test;

import static org.junit.Assert.*;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import fr.leoseb.iut.Money;

public class MoneyTest {
	
	private Money oneEur;
	private Money oneDoll;

	@Before
	public void setUp() throws Exception {
		oneEur = new Money(1.0, "EUR");
		oneDoll = new Money(1.0, "USD");
	}
	
	@Test
	public void testConstructeurOK() {
		assertThat(oneEur.getAmount(), IsEqual.equalTo(1.0));
		assertThat(oneEur.getCurrency(), IsEqual.equalTo("EUR"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructeurAmountSupOuEgal0() {
		new Money(-1.0, "EUR");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructeurCurrencyNull() {
		new Money(1.0, null);
	}
	
	@Test
	public void testGetter() {
		assertThat(oneEur.getAmount(), IsEqual.equalTo(1.0));
		assertThat(oneEur.getCurrency(), IsEqual.equalTo("EUR"));
	}
	
	@Test
	public void testAddPlus0() {
		oneEur.add(0.0, "EUR");
		assertThat(oneEur.getAmount(), IsEqual.equalTo(1.0));
		oneDoll.add(0.0, "USD");
		assertThat(oneDoll.getAmount(), IsEqual.equalTo(1.0));
	}
	
	@Test
	public void testAddPlus1MemeCurr() {
		oneEur.add(1.0, "EUR");
		assertThat(oneEur.getAmount(), IsEqual.equalTo(2.0));
		oneDoll.add(1.0, "USD");
		assertThat(oneDoll.getAmount(), IsEqual.equalTo(2.0));
	}
	
	@Test
	public void testAddPlus1DiffCurr() {
		oneEur.add(1.0, "USD");
		assertThat(oneEur.getAmount(), IsEqual.equalTo(2.29));
		oneDoll.add(1.0, "EUR");
		assertThat(oneDoll.getAmount(), IsEqual.equalTo(1.0+1.0/1.29));
	}
	

	@Test
	public void testAddMoneyEUR() {
		oneEur.add(oneDoll);
		assertThat(oneEur.getAmount(), IsEqual.equalTo(2.29));
	}
	
	@Test
	public void testAddMoneyUSD() {
		oneDoll.add(oneEur);
		assertThat(oneDoll.getAmount(), IsEqual.equalTo(1.0+1.0/1.29));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddMoneyCurr() {
		oneEur.add(1.0, "GBP");
	}
}
