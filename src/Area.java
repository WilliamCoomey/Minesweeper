public class Area
{
	//status:
	//0 = hidden
	//1 = revealed
	//2 = flagged
	private int x, y, value, status;
	private boolean isMine;
	
	public Area(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public boolean isMine()
	{
		return isMine;
	}

	public void setMine(boolean isMine)
	{
		this.isMine = isMine;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}
	
	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}
}
