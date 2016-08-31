package rs.ac.bg.etf.security;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BasicConstraintsInfo implements Serializable {

	boolean isCA, critical;
	int pathLen;
	
	public BasicConstraintsInfo(boolean isCA, int pathLen, boolean c) {
		super();
		critical = c;
		this.isCA = isCA;
		this.pathLen = pathLen;
	}
	
	public boolean isCritical() {
		return critical;
	}
	public void setCritical(boolean critical) {
		this.critical = critical;
	}
	public boolean isCA() {
		return isCA;
	}
	public void setCA(boolean isCA) {
		this.isCA = isCA;
	}
	public int getPathLen() {
		return pathLen;
	}
	public void setPathLen(int pathLen) {
		this.pathLen = pathLen;
	}

	@Override
	public String toString() {
		return "BasicConstraints: [isCA=" + isCA + ", critical=" + critical
				+ ", pathLen=" + pathLen + "]";
	}
}
