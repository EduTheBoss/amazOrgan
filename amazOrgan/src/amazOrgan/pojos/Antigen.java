package amazOrgan.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Antigen implements Serializable {

	
	private static final long serialVersionUID = -228773951826510194L;
	
	Integer ID;
	boolean A;
	boolean B;
	boolean C;
	boolean DP;
	boolean DQ;
	boolean DR;
	
	
	
	//Empty constructor
	public Antigen() {
		super();
	}

	//getters and setters for the attributes
	public Integer getID() {
		return ID;
	}


	public void setID(Integer iD) {
		ID = iD;
	}


	public boolean isA() {
		return A;
	}


	public void setA(boolean a) {
		A = a;
	}


	public boolean isB() {
		return B;
	}


	public void setB(boolean b) {
		B = b;
	}


	public boolean isC() {
		return C;
	}


	public void setC(boolean c) {
		C = c;
	}


	public boolean isDP() {
		return DP;
	}


	public void setDP(boolean dP) {
		DP = dP;
	}


	public boolean isDQ() {
		return DQ;
	}


	public void setDQ(boolean dQ) {
		DQ = dQ;
	}


	public boolean isDR() {
		return DR;
	}


	public void setDR(boolean dR) {
		DR = dR;
	}


	@Override
	public String toString() {
		return "Antigen [ID=" + ID + ", A=" + A + ", B=" + B + ", C=" + C + ", DP=" + DP + ", DQ=" + DQ + ", DR=" + DR
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(A, B, C, DP, DQ, DR, ID);
	}

	//equals uses all of the attributes
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Antigen other = (Antigen) obj;
		return A == other.A && B == other.B && C == other.C && DP == other.DP && DQ == other.DQ && DR == other.DR
				&& Objects.equals(ID, other.ID);
	}
	
	
	
	
}
