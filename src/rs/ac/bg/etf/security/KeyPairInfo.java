package rs.ac.bg.etf.security;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.KeyPair;
import java.util.Date;

@SuppressWarnings("serial")
public class KeyPairInfo implements Serializable {

	int keySize, version = 3;
	long validity;
	Date notBefore, notAfter;
	BigInteger serialNumber;
	String CN, OU, O, L, ST, C, E, alias;
	KeyPair keyPair;
	boolean signed = false;
	BasicConstraintsInfo bci;
	IssuerAltNameInfo iani;
	KeyUsageInfo kui;
	byte[] signature = null;
	String sigAlg = "";
	public KeyPairInfo(){
		
	}
	
	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public String getSigAlg() {
		return sigAlg;
	}

	public void setSigAlg(String sigAlg) {
		this.sigAlg = sigAlg;
	}

	public KeyUsageInfo getKui() {
		return kui;
	}
	public void setKui(KeyUsageInfo kui) {
		this.kui = kui;
	}
	public IssuerAltNameInfo getIani() {
		return iani;
	}
	public void setIani(IssuerAltNameInfo iani) {
		this.iani = iani;
	}
	public BasicConstraintsInfo getBci() {
		return bci;
	}
	public void setBci(BasicConstraintsInfo bci) {
		this.bci = bci;
	}
	public long getValidity() {
		return validity;
	}
	public void setValidity(long validity) {
		this.validity = validity;
		notBefore = new Date();
		notAfter = new Date(notBefore.getTime() + validity * 24 * 60 * 60 * 1000);
	}
	public int getKeySize() {
		return keySize;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public void setKeySize(int keySize) {
		this.keySize = keySize;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Date getNotBefore() {
		return notBefore;
	}
	public void setNotBefore(Date notBefore) {
		this.notBefore = notBefore;
	}
	public Date getNotAfter() {
		return notAfter;
	}
	public void setNotAfter(Date notAfter) {
		this.notAfter = notAfter;
	}
	public BigInteger getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(BigInteger serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getCN() {
		return CN;
	}
	public void setCN(String cN) {
		CN = cN;
	}
	public String getOU() {
		return OU;
	}
	public void setOU(String oU) {
		OU = oU;
	}
	public String getO() {
		return O;
	}
	public void setO(String o) {
		O = o;
	}
	public String getL() {
		return L;
	}
	public void setL(String l) {
		L = l;
	}
	public String getST() {
		return ST;
	}
	public void setST(String sT) {
		ST = sT;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		C = c;
	}
	public String getE() {
		return E;
	}
	public void setE(String e) {
		E = e;
	}
	public KeyPair getKeyPair() {
		return keyPair;
	}
	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}
	public boolean isSigned() {
		return signed;
	}
	public void setSigned(boolean signed) {
		this.signed = signed;
	}
	@Override
	public String toString() {
		return "KeyPairInfo [keySize=" + keySize + ", version=" + version
				+ ", validity=" + validity + ", notBefore=" + notBefore
				+ ", notAfter=" + notAfter + ", serialNumber=" + serialNumber
				+ ", CN=" + CN + ", OU=" + OU + ", O=" + O + ", L=" + L
				+ ", ST=" + ST + ", C=" + C + ", E=" + E + ", alias=" + alias
				+ ", keyPair=" + keyPair + ", signed=" + signed + ", bci="
				+ bci + ", iani=" + iani + ", kui=" + kui + "]";
	}
	
	public String format(){
		return "CN=" + CN + ", OU=" + OU + ", O=" + O + ", L=" + L
				+ ", ST=" + ST + ", C=" + C + ", EMAILADDRESS=" + E; 
	}
	
	public String formatP12(){
		return "CN=" + CN + ", OU=" + OU + ", O=" + O + ", L=" + L
				+ ", ST=" + ST + ", C=" + C + ", STREET=" + E; 
	}
}
