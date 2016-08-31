package rs.ac.bg.etf.security;

import java.io.Serializable;

@SuppressWarnings("serial")
public class IssuerAltNameInfo implements Serializable{

	String otherName = "", rfc822Name = "", dNSName = "", x400Address = "", directoryName = "", ediPartyName = "", uniformResourceIdentifier = "", iPAddress = "", registeredID = "";
	boolean isCritical = false;
	
	public boolean isCritical() {
		return isCritical;
	}

	public void setCritical(boolean isCritical) {
		this.isCritical = isCritical;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getRfc822Name() {
		return rfc822Name;
	}

	public void setRfc822Name(String rfc822Name) {
		this.rfc822Name = rfc822Name;
	}

	public String getdNSName() {
		return dNSName;
	}

	public void setdNSName(String dNSName) {
		this.dNSName = dNSName;
	}

	public String getX400Address() {
		return x400Address;
	}

	public void setX400Address(String x400Address) {
		this.x400Address = x400Address;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public String getEdiPartyName() {
		return ediPartyName;
	}

	public void setEdiPartyName(String ediPartyName) {
		this.ediPartyName = ediPartyName;
	}

	public String getUniformResourceIdentifier() {
		return uniformResourceIdentifier;
	}

	public void setUniformResourceIdentifier(String uniformResourceIdentifier) {
		this.uniformResourceIdentifier = uniformResourceIdentifier;
	}

	public String getiPAddress() {
		return iPAddress;
	}

	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}

	public String getRegisteredID() {
		return registeredID;
	}

	public void setRegisteredID(String registeredID) {
		this.registeredID = registeredID;
	}

	@Override
	public String toString() {
		return "IssuerAltNameInfo [otherName=" + otherName + ", rfc822Name="
				+ rfc822Name + ", dNSName=" + dNSName + ", x400Address="
				+ x400Address + ", directoryName=" + directoryName
				+ ", ediPartyName=" + ediPartyName
				+ ", uniformResourceIdentifier=" + uniformResourceIdentifier
				+ ", iPAddress=" + iPAddress + ", registeredID=" + registeredID
				+ ", isCritical=" + isCritical + "]";
	}
	
	public String format(){
		return "otherName=" + otherName + ", rfc822Name="
				+ rfc822Name + ", dNSName=" + dNSName + ", x400Address="
				+ x400Address + ", directoryName=" + directoryName
				+ ", ediPartyName=" + ediPartyName
				+ ", uniformResourceIdentifier=" + uniformResourceIdentifier
				+ ", iPAddress=" + iPAddress + ", registeredID=" + registeredID
				+ ", isCritical=" + isCritical;
	}
}
