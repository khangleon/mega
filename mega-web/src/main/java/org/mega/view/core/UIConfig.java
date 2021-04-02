package org.mega.view.core;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.mega.common.Lib;

@ManagedBean(name = "uiconfig")
@SessionScoped
public class UIConfig {
	private String locale = Lib.getResource(null, "common.locale");
	private String timeZone = Lib.getResource(null, "common.timezone");

	private Locale iuLocale = Locale.forLanguageTag(locale);

	private String currencyFraction = Lib.getResource(iuLocale, "currency.fraction");
	private String foreignFraction = Lib.getResource(iuLocale, "currency.foreign");
	private String currencyCode = Lib.getResource(iuLocale, "currency.code");
	private String currencySymbol = Lib.getResource(iuLocale, "currency.symbol");
	private String patternDate = Lib.getResource(iuLocale, "pattern.date");
	private String patternDateTime = Lib.getResource(iuLocale, "pattern.datetime");
	private String patternDateTimeShort = Lib.getResource(iuLocale, "pattern.datetimeshort");
	private String patternTime = Lib.getResource(iuLocale, "pattern.time");
	private String patternTimeShort = Lib.getResource(iuLocale, "pattern.timeshort");

	public UIConfig() {
		super();
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getCurrencyFraction() {
		return currencyFraction;
	}

	public void setCurrencyFraction(String currencyFraction) {
		this.currencyFraction = currencyFraction;
	}

	public String getForeignFraction() {
		return foreignFraction;
	}

	public void setForeignFraction(String foreignFraction) {
		this.foreignFraction = foreignFraction;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getPatternDate() {
		return patternDate;
	}

	public void setPatternDate(String patternDate) {
		this.patternDate = patternDate;
	}

	public String getPatternDateTime() {
		return patternDateTime;
	}

	public void setPatternDateTime(String patternDateTime) {
		this.patternDateTime = patternDateTime;
	}

	public String getPatternDateTimeShort() {
		return patternDateTimeShort;
	}

	public void setPatternDateTimeShort(String patternDateTimeShort) {
		this.patternDateTimeShort = patternDateTimeShort;
	}

	public String getPatternTime() {
		return patternTime;
	}

	public void setPatternTime(String patternTime) {
		this.patternTime = patternTime;
	}

	public String getPatternTimeShort() {
		return patternTimeShort;
	}

	public void setPatternTimeShort(String patternTimeShort) {
		this.patternTimeShort = patternTimeShort;
	}

}
