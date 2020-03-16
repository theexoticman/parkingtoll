package parkingtoll;

import parkingtoll.Convertible;

/**
 * Currency Enum, defining the different types of currency. For mor currency,
 * one can create another enum that implements convertible to be used by the
 * Price class.
 * 
 * @author jlm
 *
 */
public enum Currency implements Convertible {
	EUROS, DOllARS, REAIS, PESOS;

}
