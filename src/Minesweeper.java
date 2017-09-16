import java.util.Random;
import java.util.Scanner;

//@SuppressWarnings("unused")
public class Minesweeper
{
	static int mapSize = 10, numberOfMines = 10;//Overflow error at 58
	static Scanner k = new Scanner(System.in);
	static Area[][] map = new Area[mapSize][mapSize];
	
	public static void main(String[] args)
	{
		boolean running = true;
		
		initalizeMap();

		while(running)
		{
			drawMap(map);
			running = showMenu();
		}
			
	}
	
	public static boolean showMenu()
	{
//		int choice = -1;
		String input;
		boolean running = true;
		
		System.out.println("r[x][y] - to reveal an area");
		System.out.println("f[x][y] - to flag an area");
		System.out.println("map - to reveal the map");
		System.out.println("exit - to stop the game");

		input = Input.getString();
		
		if(input.charAt(0) == 'r')
		{
			if(input.length() == 3)
			{
				int x = Integer.parseInt(input.substring(1,2));
				int y = Integer.parseInt(input.substring(2,3));
				revealArea(map[x][y]);
			}
			else
				System.out.println("Invlaid input");
		}
		else if(input.charAt(0) == 'f')
		{
			if(input.length() == 3)
			{
				int x = Integer.parseInt(input.substring(1,2));
				int y = Integer.parseInt(input.substring(2,3));
				flagArea(map[x][y]);
			}
			else
				System.out.println("Invalid input");
		}
		else if(input.charAt(0) == 'm')
		{
			if(input.length() == 3)
			{
				if(input.equalsIgnoreCase("map"))
					revealMap();
			}
			else
				System.out.println("Invalid input");
		}
		else if(input.charAt(0) == 'e' || input.charAt(0) == 'q')
		{
			if(input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit"))
				running = false;
			else
				System.out.println("Invalid input");
		}
		else
		{
			System.out.println("Invalid input");
		}
		
		return running;
	}
	
	public static void revealMap()
	{
		for(int i=0;i<mapSize;i++)
			for(int j=0;j<mapSize;j++)
				map[j][i].setStatus(1);
	}
	
	public static void flagArea(Area area)
	{
		area.setStatus(2);
	}
	
	public static void revealArea(Area area)
	{
		area.setStatus(1);
		
		if(area.isMine() == true)
			revealMap();
		else if(area.getValue() == 0)
			checkNearbyAreas(area);
	}

	public static void checkNearbyAreas(Area area)
	{
		int x = area.getX();
		int y = area.getY();
		
		if(x == 0 && y == 0)
			checkValueBottomRight(area);
		else if(x == (mapSize - 1) && y == 0)
			checkValueBottomLeft(area);
		else if(x == 0 && y == (mapSize - 1))
			checkValueTopRight(area);
		else if(x == (mapSize - 1) && y == (mapSize - 1))
			checkValueTopLeft(area);
		else if(x != (mapSize - 1) && x != 0 && y == 0)
			checkValueBottom(area);
		else if(x == (mapSize - 1) && y != 0 && y != (mapSize - 1))
			checkValueLeft(area);
		else if(x != 0 && x != (mapSize - 1) && y == (mapSize - 1))
			checkValueTop(area);
		else if(x == 0 && y != 0 && y != (mapSize - 1))
			checkValueRight(area);
		else
			checkValueFull(area);
	}
	
	public static void checkForValue(Area area)
	{
		if(area.getValue() == 0 && area.getStatus() == 0 && area.isMine() == false)
			revealArea(area);
		else if(area.getValue() != 0 && area.getStatus() == 0 && area.isMine() == false)
			revealArea(area);
	}
	
	public static void checkValueFull(Area area)
	{
		int x = area.getX();
		int y = area.getY();
		
		checkForValue(map[x-1][y-1]);//Top-Left
		checkForValue(map[x][y-1]);//Top
		checkForValue(map[x+1][y-1]);//Top-right
		checkForValue(map[x+1][y]);//Right
		checkForValue(map[x+1][y+1]);//Bottom-right
		checkForValue(map[x][y+1]);//Bottom
		checkForValue(map[x-1][y+1]);//Bottom-left
		checkForValue(map[x-1][y]);//Left
	}
	
	public static void checkValueRight(Area area)
	{
		int x = area.getX();
		int y = area.getY();
		
		checkForValue(map[x][y-1]);//Top
		checkForValue(map[x+1][y-1]);//Top-right
		checkForValue(map[x+1][y]);//Right
		checkForValue(map[x+1][y+1]);//Bottom-right
		checkForValue(map[x][y+1]);//Bottom
	}
	
	public static void checkValueTop(Area area)
	{
		int x = area.getX();
		int y = area.getY();
		
		checkForValue(map[x-1][y]);//Left
		checkForValue(map[x-1][y-1]);//Top-Left
		checkForValue(map[x][y-1]);//Top
		checkForValue(map[x+1][y-1]);//Top-right
		checkForValue(map[x+1][y]);//Right
	}
	
	public static void checkValueTopRight(Area area)
	{
		int x = area.getX();
		int y = area.getY();
		
		checkForValue(map[x][y-1]);//Top
		checkForValue(map[x+1][y-1]);//Top-right
		checkForValue(map[x+1][y]);//Right
	}
	
	public static void checkValueTopLeft(Area area)
	{
		int x = area.getX();
		int y = area.getY();
		
		checkForValue(map[x-1][y]);//Left
		checkForValue(map[x-1][y-1]);//Top-Left
		checkForValue(map[x][y-1]);//Top
	}
	
	public static void checkValueLeft(Area area)
	{
		int x = area.getX();
		int y = area.getY();
		
		checkForValue(map[x][y+1]);//Bottom
		checkForValue(map[x-1][y+1]);//Bottom-left
		checkForValue(map[x-1][y]);//Left
		checkForValue(map[x-1][y-1]);//Top-Left
		checkForValue(map[x][y-1]);//Top
	}
	
	public static void checkValueBottomLeft(Area area)
	{
		int x = area.getX();
		int y = area.getY();
		
		checkForValue(map[x][y+1]);//Bottom
		checkForValue(map[x-1][y+1]);//Bottom-left
		checkForValue(map[x-1][y]);//Left
	}
	
	public static void checkValueBottom(Area area)
	{
		int x = area.getX();
		int y = area.getY();
		
		checkForValue(map[x+1][y]);//Right
		checkForValue(map[x+1][y+1]);//Bottom-right
		checkForValue(map[x][y+1]);//Bottom
		checkForValue(map[x-1][y+1]);//Bottom-left
		checkForValue(map[x-1][y]);//Left
	}
	
	public static void checkValueBottomRight(Area area)
	{
		int x = area.getX();
		int y = area.getY();
		
		checkForValue(map[x+1][y]);//Right
		checkForValue(map[x+1][y+1]);//Bottom-right
		checkForValue(map[x][y+1]);//Bottom
	}
	
	public static int checkFull(int x, int y)
	{
		int value = 0;
		
		if(map[x-1][y-1].isMine() == true)//Top-left
			value++;
		if(map[x][y-1].isMine() == true)//Top
			value++;
		if(map[x+1][y-1].isMine() == true)//Top-right
			value++;
		if(map[x+1][y].isMine() == true)//Right
			value++;
		if(map[x+1][y+1].isMine() == true)//Bottom-right
			value++;
		if(map[x][y+1].isMine() == true)//Bottom
			value++;
		if(map[x-1][y+1].isMine() == true)//Bottom-left
			value++;
		if(map[x-1][y].isMine() == true)//Left
			value++;
		
		return value;
	}
	
	public static int checkRight(int x, int y)
	{
		int value = 0;
		
		if(map[x][y-1].isMine() == true)//Top
			value++;
		if(map[x+1][y-1].isMine() == true)//Top-right
			value++;
		if(map[x+1][y].isMine() == true)//Right
			value++;
		if(map[x+1][y+1].isMine() == true)//Bottom-right
			value++;
		if(map[x][y+1].isMine() == true)//Bottom
			value++;
		
		return value;
	}
	
	public static int checkTop(int x, int y)
	{
		int value = 0;
		
		if(map[x-1][y].isMine() == true)//Left
			value++;
		if(map[x-1][y-1].isMine() == true)//Top-left
			value++;
		if(map[x][y-1].isMine() == true)//Top
			value++;
		if(map[x+1][y-1].isMine() == true)//Top-right
			value++;
		if(map[x+1][y].isMine() == true)//Right
			value++;
		
		return value;
	}
	
	public static int checkTopRight(int x, int y)
	{
		int value = 0;
		
		if(map[x][y-1].isMine() == true)//Top
			value++;
		if(map[x+1][y-1].isMine() == true)//Top-right
			value++;
		if(map[x+1][y].isMine() == true)//Right
			value++;
		
		return value;
	}
	
	public static int checkTopLeft(int x, int y)
	{
		int value = 0;
		
		if(map[x-1][y].isMine() == true)//Left
			value++;
		if(map[x-1][y-1].isMine() == true)//Top-left
			value++;
		if(map[x][y-1].isMine() == true)//Top
			value++;
		
		return value;
	}
	
	public static int checkLeft(int x, int y)
	{
		int value = 0;
		
		if(map[x][y+1].isMine() == true)//Bottom
			value++;
		if(map[x-1][y+1].isMine() == true)//Bottom-left
			value++;
		if(map[x-1][y].isMine() == true)//Left
			value++;
		if(map[x-1][y-1].isMine() == true)//Top-left
			value++;
		if(map[x][y-1].isMine() == true)//Top
			value++;
		
		return value;
	}
	
	public static int checkBottomLeft(int x, int y)
	{
		int value = 0;
		
		if(map[x][y+1].isMine() == true)//Bottom
			value++;
		if(map[x-1][y+1].isMine() == true)//Bottom-left
			value++;
		if(map[x-1][y].isMine() == true)//Left
			value++;
		
		return value;
	}
	
	public static int checkBottom(int x, int y)
	{
		int value = 0;
		
		if(map[x+1][y].isMine() == true)//Right
			value++;
		if(map[x+1][y+1].isMine() == true)//Bottom-right
			value++;
		if(map[x][y+1].isMine() == true)//Bottom
			value++;
		if(map[x-1][y+1].isMine() == true)//Bottom-left
			value++;
		if(map[x-1][y].isMine() == true)//Left
			value++;
		
		return value;
	}
	
	public static int checkBottomRight(int x, int y)
	{
		int value = 0;
		
		if(map[x+1][y].isMine() == true)//Right
			value++;
		if(map[x+1][y+1].isMine() == true)//Bottom-right
			value++;
		if(map[x][y+1].isMine() == true)//Bottom
			value++;
		
		return value;
	}
	
	public static void drawMap(Area[][] map)
	{
		System.out.print("___");
		for(int i=0;i<mapSize;i++)
			if(i<10)
				System.out.print("0"+i+"_");
			else
				System.out.print(i+"_");
		
		System.out.println();
		
		for(int i=0;i<mapSize;i++)
		{
			if(i<10)
				System.out.print(" "+i+"|");
			else
				System.out.print(i+"|");
			for(int j=0;j<mapSize;j++)
			{
				if(map[j][i].isMine() == true && map[j][i].getStatus() == 1)
					System.out.print(" x|");
				else if(map[j][i].getStatus() == 0)
					System.out.print("__|");
				else if(map[j][i].getStatus() == 1)
					System.out.print(" "+map[j][i].getValue()+"|");
				else if(map[j][i].getStatus() == 2)
					System.out.print(" f|");
			}
			System.out.println();
		}
	}
	
	public static void initalizeMap()
	{
		Random random = new Random();
		int actualNumberOfMines = 0;
		
		for(int i=0;i<mapSize;i++)
			for(int j=0;j<mapSize;j++)
				map[j][i] = new Area(j,i);
		
		while(actualNumberOfMines < numberOfMines)
		{
			int x = random.nextInt(mapSize);
			int y = random.nextInt(mapSize);
			
			if(map[x][y].isMine() == false)
			{
				map[x][y].setMine(true);
				actualNumberOfMines++;
			}
		}
		
		for(int y=0;y<mapSize;y++)
			for(int x=0;x<mapSize;x++)
				if(x == 0 && y == 0)
					map[x][y].setValue(checkBottomRight(x, y));
				else if(x == (mapSize - 1) && y == 0)
					map[x][y].setValue(checkBottomLeft(x, y));
				else if(x == 0 && y == (mapSize - 1))
					map[x][y].setValue(checkTopRight(x, y));
				else if(x == (mapSize - 1) && y == (mapSize - 1))
					map[x][y].setValue(checkTopLeft(x, y));
				else if(x != (mapSize - 1) && x != 0 && y == 0)
					map[x][y].setValue(checkBottom(x, y));
				else if(x == (mapSize - 1) && y != 0 && y != (mapSize - 1))
					map[x][y].setValue(checkLeft(x, y));
				else if(x != 0 && x != (mapSize - 1) && y == (mapSize - 1))
					map[x][y].setValue(checkTop(x, y));
				else if(x == 0 && y != 0 && y != (mapSize - 1))
					map[x][y].setValue(checkRight(x, y));
				else
					map[x][y].setValue(checkFull(x, y));
	}
	
}


















