public class Marks
{
    private int BM; // Subject Bahasa Melayu
    private int BC; // Subject Chinese
    private int BI; // Subject English
    private int MM; // Subject Mathematics
    private int SC; // Subject Science

    // Constructor
    public Marks(int BM, int BC, int BI, int MM, int SC)
    {
        this.BM = BM;
		this.BC = BC;
		this.BI = BI;
		this.MM = MM;
		this.SC = SC;
    }

    // Accessors and Mutators
    public int getBM()
    {
        return BM;
    }

	public void setBM(int BM)
    {
    	this.BM = BM;
    }

    public int getBC()
    {
        return BC;
    }

	public void setBC(int BC)
    {
        this.BC = BC;
    }

    public int getBI()
    {
        return BI;
    }

	public void setBI(int BI)
    {
        this.BI = BI;
    }

    public int getMM()
    {
        return MM;
    }

	public void setMM(int MM)
    {
        this.MM = MM;
    }

    public int getSC()
    {
        return SC;
    }

    public void setSC(int SC)
    {
        this.SC = SC;
    }
}
