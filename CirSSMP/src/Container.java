
public class Container implements Comparable<Container>,Cloneable{
	public int o;//it stands for the origin port.
	public int d;//it stands for the destination port.
    public int Seqnum;//it stands for the number to be loaded at the first time.
	public Container() {
		super();
		this.o = o;
		this.d = d;
	}

	public int getO() {
		return o;
	}

	public void setO(int o) {
		this.o = o;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public Container clone() {
		try {
			return (Container)super.clone();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public int compareTo(Container c) {
		if (this.o < c.o) return this.o-c.o;
		if (this.o > c.o) return this.o-c.o;
		if (this.o < this.d && c.o < c.d) return c.d-this.d;//this.d-c.d;
		if (this.o > this.d && c.o > c.d) return c.d-this.d;//this.d-c.d;
		if (this.o > this.d && c.o < c.d) return this.d-c.d;//c.d-this.d;
		if (this.o < this.d && c.o > c.d) return this.d-c.d;//c.d-this.d;
		return 0;
		}

	}
	
		
	



