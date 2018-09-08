package castle;

import java.util.Scanner;
import java.util.HashMap;

public class Game {
    private Room currentRoom;
    private HashMap<String,Hand> Hands = new HashMap<String,Hand>(); 
    
        
    public Game() 
    {
    	createRooms();
        Hands.put("Help",new HandlerHelp());
        Hands.put("bye",new HandlerBye());
        Hands.put("go", new HandlerGo(this));
        Hands.put("drink", new HandlerCoffee(this));
    }

     
    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom,play,bedroom2;
        //	制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂",true);
        pub = new Room("小酒吧",true);
        study = new Room("书房");
        bedroom = new Room("卧室");
        play = new Room("休闲室");
        bedroom2 = new Room("客房",true);
        
        //	初始化房间的出口
        outside.setExit("east", lobby);
        outside.setExit("west", pub);
        outside.setExit("south", study);
        pub.setExit("east", outside);
        study.setExit("north", outside);
        study.setExit("east", bedroom);
        study.setExit("south", bedroom2);
        lobby.setExit("west", outside);
        lobby.setExit("up", play);
        bedroom.setExit("west", study);
        bedroom.setExit("up", play);
        play.setExit("down", lobby);
        bedroom2.setExit("north", study);
        
        currentRoom = outside;  //	从城堡门外开始
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到城堡！");
        System.out.println("这是一个超级无聊的游戏。");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        hint();
        }

    public void hint() {
    	System.out.println("现在你在" + currentRoom);
        System.out.print("出口有：");
        System.out.println(currentRoom.ExitsDescription());
        System.out.println();
    }
    
    // 以下为用户命令
    public void goRoom(String direction) 
    {
        Room nextRoom = null;
        nextRoom = currentRoom.getRoom(direction);
        if (nextRoom == null) {
            System.out.println("那里没有门！");
        }
        else {
            currentRoom = nextRoom;
            hint();
            }
    }
    
    public void drink() {
    	if(currentRoom.isCoffee()) {
    		System.out.println("Your coffee,Sir!");
    	}
    	else
    		System.out.println("Sorry Sir,no coffee in this room.");	
    }
	
    public void play() {
    	Scanner in = new Scanner(System.in);
    	while ( true ) {
    		String line = in.nextLine();
    		String[] words = line.split(" ");
    		String value = "";
    		if(words.length > 1)
    			value = words[1];
    		Hand hand = Hands.get(words[0]);
    		if(hand != null) {
    			hand.run(value);
    			if(hand.isBye())
    				break;
    		}
//    		if ( words[0].equals("help") ) {
//    			game.printHelp();
//    		} else if (words[0].equals("go") ) {
//    			game.goRoom(words[1]);
//    		} else if (words[0].equals("drink")) {
//    			game.drink();
//    		}else if ( words[0].equals("bye") ) {
//    			break;
//    		}
    }
    System.out.println("感谢您的光临。再见！");
    in.close();

    }
	public static void main(String[] args) {
		Game game = new Game();
		game.printWelcome();
		game.play();
        }

}
