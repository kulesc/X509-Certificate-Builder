package rs.ac.bg.etf.security;

import java.io.Serializable;

@SuppressWarnings("serial")
public class KeyUsageInfo implements Serializable{

	boolean digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment, keyAgreement, keyCertSign, cRLSign, encipherOnly, decipherOnly;
	boolean isCritical = true;
	
	public KeyUsageInfo(){
		
	}
	
	public KeyUsageInfo(boolean[] niz){
		digitalSignature = niz[0];
		nonRepudiation = niz[1];
		keyEncipherment = niz[2];
		dataEncipherment = niz[3];
		keyAgreement = niz[4];
		keyCertSign = niz[5];
		cRLSign = niz[6];
		encipherOnly = niz[7];
		decipherOnly = niz[8];
	}
	
	public boolean isDigitalSignature() {
		return digitalSignature;
	}
	public void setDigitalSignature(boolean digitalSignature) {
		this.digitalSignature = digitalSignature;
	}
	public boolean isNonRepudiation() {
		return nonRepudiation;
	}
	public void setNonRepudiation(boolean nonRepudiation) {
		this.nonRepudiation = nonRepudiation;
	}
	public boolean isKeyEncipherment() {
		return keyEncipherment;
	}
	public void setKeyEncipherment(boolean keyEncipherment) {
		this.keyEncipherment = keyEncipherment;
	}
	public boolean isDataEncipherment() {
		return dataEncipherment;
	}
	public void setDataEncipherment(boolean dataEncipherment) {
		this.dataEncipherment = dataEncipherment;
	}
	public boolean isKeyAgreement() {
		return keyAgreement;
	}
	public void setKeyAgreement(boolean keyAgreement) {
		this.keyAgreement = keyAgreement;
	}
	public boolean isKeyCertSign() {
		return keyCertSign;
	}
	public void setKeyCertSign(boolean keyCertSign) {
		this.keyCertSign = keyCertSign;
	}
	public boolean iscRLSign() {
		return cRLSign;
	}
	public void setcRLSign(boolean cRLSign) {
		this.cRLSign = cRLSign;
	}
	public boolean isEncipherOnly() {
		return encipherOnly;
	}
	public void setEncipherOnly(boolean encipherOnly) {
		this.encipherOnly = encipherOnly;
	}
	public boolean isDecipherOnly() {
		return decipherOnly;
	}
	public void setDecipherOnly(boolean decipherOnly) {
		this.decipherOnly = decipherOnly;
	}
	public boolean isCritical() {
		return isCritical;
	}
	public void setCritical(boolean isCritical) {
		this.isCritical = isCritical;
	}
	@Override
	public String toString() {
		return " digitalSignature = " + digitalSignature
				+ ", nonRepudiation = " + nonRepudiation + ", keyEncipherment = "
				+ keyEncipherment + ",\n dataEncipherment = " + dataEncipherment 
				+ ", keyAgreement = " + keyAgreement + ", keyCertSign = "
				+ keyCertSign +  ",\n cRLSign = " + cRLSign + ", encipherOnly = "
				+ encipherOnly + ", decipherOnly = " + decipherOnly
				+ ", isCritical = " + isCritical;
	}
	
	
}
