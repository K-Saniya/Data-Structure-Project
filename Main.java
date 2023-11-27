/*
 * DS LAB MINIPROJECT
 * EMAIL SYSTEM
 */

package miniproject;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class User{
	String name,email;
	private String password;
	Stack<String> inbox = new Stack<String>();
	Stack<String> outbox = new Stack<String>();
	ArrayList<String> inboxList = new ArrayList<>(); 
	ArrayList<String> starlist = new ArrayList<>(); 
	int index;
	
	
	User(String name,String email,String password,Stack inbox,Stack outbox){
		this.name = name;
		this.email = email;
		this.password = password;
		this.inbox = inbox;
		this.outbox = outbox;
		this.index = 0;
	
	}
	
	//getter method for private variable
	public String getPassword() {
		return password;
	}
	
   
	
	
}
class Methods{
    String mailId;
    String pw;
	int flag=0;
	LocalDateTime now =LocalDateTime.now();
	DateTimeFormatter format= DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
	String myObj=now.format(format);
	
	    
	
	
	public Scanner sc = new Scanner(System.in);
	
	ArrayList<User> accounts = new ArrayList<User>();
	
	void signUp(String mailId) {
		int flag = 0;
		
		do {
			flag = 2;
			     for(int i=0; i<accounts.size(); i++) {
					    if(mailId.equals(accounts.get(i).email)) {
						System.out.println("Email ID already exists!");
						flag = 1;
						break;
					}
					else {
						flag = 2;
					}
				}
			
		}
		while(flag==1);
		
		System.out.println("Enter your name : ");
		String Name = sc.next();
		
		int flag1 = 0;
		int pwd_flag=1;
		do {
			do {
			System.out.println("Enter your password :(minimum 4 characters, max 10 characters) ");
			pw = sc.next();
			if(pw.length()<4||pw.length()>10) {
				System.out.println("Invalid password, Please follow the character limits");
				pwd_flag=0;}
			else {
				break;
			}
			
			}while(pwd_flag==0);
			
			//checking for duplicate password
			for(int i=0; i<accounts.size(); i++) {
				
				if(pw.equals(accounts.get(i).getPassword() )) {
					System.out.println("Password already exists!");
					flag1 = 1;
					break;
				}
				else {
					flag1 = 2;
				}
			}//for
		}while(flag1 == 1);
		
		
		//creating inbox stack for new user
		Stack<String> inbox = new Stack<String>();
		//creating outbox stack for new user
		Stack<String> outbox = new Stack<String>();
		
		
		//adding account to list
		User user = new User(Name,mailId,pw,inbox,outbox);
		accounts.add(user);
	}


	void login(String email)
	{
	    	flag = 0;
			
					        do{
						    System.out.println("Enter your password : ");
		                	pw = sc.next();
		                	//checking for wrong password
		                	
		                	for
		                	(int i=0; i<accounts.size(); i++) {
		                		if(pw.equals(accounts.get(i).getPassword() )) {
				                	flag = 1;
					                break;
				            }}
			            	if(flag==0) {
				                System.out.println("password doesn't match!!!!\nEnter again");
				              //	flag=0;
			    	}
		                	
				
			              	
				}while(flag==0);
		
}

	void draft(String senderMail) {
		
		System.out.println("Enter mail id of receiver : ");
		String receiverMail = sc.next();
		
		int rm = 0; //index for sender mail id
		for(int i=0; i<accounts.size(); i++) {
			if(accounts.get(i).email.equals(senderMail)) {
				rm = i;
			}
		}
		
		//checking if receiver mail exists
		for(int j=0; j<accounts.size(); j++) {
			if(receiverMail.equals(accounts.get(j).email)) {
				sc.nextLine();
				System.out.println("Enter your message : ");
				String message = sc.nextLine();
				
				//push in outbox of senderMail
				accounts.get(rm).outbox.push("To => "+receiverMail+" "+myObj+"\nMessage : "+message);
				
				//push in inbox of receiver Mail
				accounts.get(j).index++;  //increment index
				accounts.get(j).inbox.push(accounts.get(j).index+" : "+"From => "+senderMail+" "+myObj+"\nMessage : "+message);
				accounts.get(j).inboxList.add(accounts.get(j).index+" : "+"From => "+senderMail+" "+myObj+"\nMessage : "+message);
				
				return;
			}
		}
		
		System.out.println("Account not found!");
	}
	
	void Outbox(String senderEmail) {
	
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).email.equals(senderEmail)) {
                
               // System.out.println("Messages sent by " + senderEmail + " :");
                
                if(accounts.get(i).outbox.isEmpty()) {
                	System.out.println("NO MESSAGES YET!! ");
                	break;
                }
                else {
                	 
                     
                for (String message : accounts.get(i).outbox) {
                	 
                   System.out.println(message);
                   System.out.println();
                }}
           
            }
        
        
       }
	}
	
	void inbox(String mail) {
		//checking for matching id
		//assuming login is successful mail id is present
		for(int i=0; i<accounts.size(); i++) {
			if(accounts.get(i).email.equals(mail)) {
				//if inbox empty
				if(accounts.get(i).inbox.isEmpty()) {
					System.out.println("No messages yet!");
					return;
				}

				
				//display inbox of that email user
				for(String element : accounts.get(i).inbox) {
					System.out.println(element);
					System.out.println();
				}
				 
				}
		}
		
	}
	void forward(String sender) {
		String forward = "";
		int sender_index = 0;
		
		for(int i=0; i<accounts.size(); i++) {
			if(accounts.get(i).email.equals(sender)) {
				//saving index of sender
				sender_index = i;
				if(accounts.get(i).index==0) {
					System.out.println("No messages yet!!");
					return;
				}
			
				//display inbox
				inbox(sender);
				System.out.println("\nEnter index of the mail you want to forward : ");
				int to_forward = sc.nextInt();
				if(to_forward>accounts.get(i).inboxList.size() || to_forward<1) {
					
					System.out.println("Index does not exist !! \nEnter option 5 again");
					return;
				}
			
				forward = accounts.get(i).inboxList.get(to_forward-1);
				//System.out.println(forward);
				}
		}
		System.out.println("Enter the email address of the recipient to whom you'd like to forward this email : ");
		String receiver = sc.next();
		for(int i=0;i<accounts.size();i++) {
			if(accounts.get(i).email.equals(receiver)) 
			{
				accounts.get(i).index++;
				accounts.get(i).inbox.push(accounts.get(i).index+" : "+"From => "+sender+" "+myObj+"\nMessage : "+forward);
				accounts.get(i).inboxList.add(accounts.get(i).index+" : "+"From => "+sender+" "+myObj+"\nMessage : "+forward);
				accounts.get(sender_index).outbox.push("To => "+receiver+" "+myObj+"\nMessage : "+forward);
				return;
			}
		}
		System.out.println("User not found!");
	}
	
	
	void delete() {
       // login(); // Call login method first

        System.out.println("Are you sure you want to permanently delete your account?");
        System.out.println("Enter 1 for Yes, 0 for No: ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character left by nextInt()

        if (choice == 1) {
            // Account deletion logic (same as before)
            System.out.println("please Re-Enter your E-mail ID to confirm deletion: ");
            String emailToDelete = sc.nextLine();
            int indexToDelete = -1;

            for (int i = 0; i < accounts.size(); i++) {
                if (emailToDelete.equals(accounts.get(i).email)) {
                    indexToDelete = i;
                    break;
                }
            }

            if (indexToDelete != -1) {
                accounts.remove(indexToDelete);
                System.out.println("Account deleted successfully.");
            }
        } else {
            System.out.println("Account deletion canceled.");
        }
    }
	
	void find(String mail) {
		int flag=0;
	    for(int i=0; i<accounts.size(); i++) {
	        if(accounts.get(i).email.equals(mail)) {
	           flag=1;
	           System.out.println("You are an existing user!");
	           break;
	         }
	    }
	   if(flag==1) {
		  login(mail);
		}
	   else {
		signUp(mail);
	 }
		
	
    }


	void deleteMsg(String mail)
	{
		
			
		    
		    for (int i = 0; i < accounts.size(); i++) {
		        if (accounts.get(i).email.equals(mail)) {
		        	if(accounts.get(i).index==0) {
						System.out.println("No messages yet!!");
						return;
					}
		            inbox(mail);
				    System.out.println("Enter index of the message you want to delete :");
				    int del = sc.nextInt();
		        	
		            Stack<String> inbox = accounts.get(i).inbox;
		            int index = 1;
		            Stack<String> temp = new Stack<String>();

		            while (!inbox.isEmpty()) {
		                String message = inbox.pop();
		                int index_msg = Integer.parseInt(String.valueOf(message.charAt(0)));
		                if (index != del) {
		                    temp.push(message);
		                }
		                index++;
		            }

		            // Clear the inbox stack
		            inbox.clear();

		            // Push back the messages from the temporary stack
		            while (!temp.isEmpty()) {
		                inbox.push(temp.pop());
		            }

		            accounts.get(i).index--;
		            System.out.println("Message with index " + del + " deleted from inbox.");
		            return;
		        }
		    }
		    System.out.println("Message not found in inbox.");
		}
	void star_a_msg(String mail) {
		inbox(mail);
	   int putstar=0;
	    String str="";
	  
	    for (int i = 0; i < accounts.size(); i++) {
	        if (accounts.get(i).email.equals(mail)) {
	        	
	            if( accounts.get(i).inbox.isEmpty()) {
	            	System.out.println("Inbox is empty!");
	            	return;
	            }//if empty
	            
	            
	            else {
	            	
	            	
	            	System.out.println("Enter index of message to be starred");
	            	putstar=sc.nextInt();
	            	//validation for out of bound index input
	            	if(putstar>accounts.get(i).inboxList.size() || putstar<1) {
						
						System.out.println("Index does not exist !! \nEnter option 6 again");
						return;
					}
	            	
	            	
	            	str=accounts.get(i).inboxList.get(putstar-1);
	            	System.out.println("STAREED"+str);
	            	
	            	//creating queue to put msgs
	            	
	               accounts.get(i).starlist.add(str);//attach the message to be starred
	           
	            System.out.println("Message with index " + putstar + " is Starred");
	            return;
	        }
	    }//if mail authenticated
	   
	}//for
	}//strmsg
	
	
	void display_starred(String mail) {
		
	for(int i=0;i<accounts.size();i++) {
		if( accounts.get(i).starlist.isEmpty()) {
        	System.out.println("No starred messages");
        	return;
        }//if empty
        else {
        	//displaying starqueue
        	System.out.println("Displaying starred messages ==>");
        	//displaying star messages from starl here:
        	//for( int j=0;j<accounts.get(i).starlist.size();j++)
        	//{	 
        	for (String starredMessage : accounts.get(i).starlist) {
                System.out.println(starredMessage);
            }
        	return;}//else
            //}
        		//System.out.println(accounts.get(i).starlist.get(j));
        	}//forsmall
        	
        }//disp
	}//class
	




public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
	    Methods obj = new Methods();
	    System.out.println("**Email system**");
	    int choice=0,cont;
	    String mail="";
	    do {
	    	
	    	 int flag=0;
	  	   
	    while(flag==0){ 
		System.out.println("Enter mail id: ");
		mail=sc.next();
		    if(mail.endsWith("@ccoew.in")) {
	    		obj.find(mail);
	    		flag=1;
	    		break;
	    	}else {
				System.out.println("Invalid E-mail ID!");
				System.out.println("Make sure it ends with @ccoew.in");
				flag = 0;
	    	}
}
		    
		    do {
		    	System.out.println();
		    	System.out.println("*****************************************************************");
		    	System.out.println("MENU");
		    	System.out.println("0.Log out");
		    	System.out.println("1.Draft a mail.");
		    	System.out.println("2.Display Inbox");
		    	System.out.println("3.Display Outbox");
		    	System.out.println("4.Delete a particular message");
		    	System.out.println("5.Forward a email");
		    	System.out.println("6.Star a message");
		    	System.out.println("7.Display Starred messages");
		    	System.out.println("8.Delete your account");
		    	System.out.println("Enter choice : ");
		    	choice = sc.nextInt();
		        System.out.println();
		    	
		    	switch(choice)
		    	{
		    	case 0:
		    		break;
		    	case 1:
		    		obj.draft(mail);
		    		System.out.println();
		    		break;
		    	case 2:
		    		obj.inbox(mail);
		    		System.out.println();
		    		break;
		    	case 3:
		    		obj.Outbox(mail);
		    		System.out.println();
		    		break;
		    	case 4:
		    		obj.deleteMsg(mail);
		    		break;
		    	case 5:
		    		obj.forward(mail);
		    		break;
		    	case 6:
		    		obj.star_a_msg(mail);
		    		break;
		    	case 7:
		    		obj.display_starred(mail);
		    		break;
		    	case 8:
		    		obj.delete();
		    		System.out.println();
		    		choice=0;
		    		
		    		break;
		    		
		    	default:
		    		System.out.println("Enter valid choice");
		    		System.out.println();
		    		
		    	}
		    	
		    }while(choice!=0);
		    
		    System.out.println("Do you want to sign in another account?\nEnter 1 for YES");
		    cont = sc.nextInt();
		    System.out.println();
	    }
	    while(cont==1);
		
	}}	
		
		/*
		 * 
		 OUTPUT:
		 
		 * **Email system**
Enter mail id: 
aws
Invalid E-mail ID!
Make sure it ends with @ccoew.in
Enter mail id: 
a@ccoew.in
Enter your name : 
saniya
Enter your password :(minimum 4 characters, max 10 characters) 
123
Invalid password, Please follow the character limits
Enter your password :(minimum 4 characters, max 10 characters) 
1234

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
2

No messages yet!


*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
0

Do you want to sign in another account?
Enter 1 for YES
1

Enter mail id: 
b@ccoew.in
Enter your name : 
radha
Enter your password :(minimum 4 characters, max 10 characters) 
3456

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
1

Enter mail id of receiver : 
a@ccoew.in
Enter your message : 
hi B here


*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
2

No messages yet!


*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
3

To => a@ccoew.in 08-11-2023  23:07:54
Message : hi B here



*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
0

Do you want to sign in another account?
Enter 1 for YES
1

Enter mail id: 
c@ccoew.in
Enter your name : 
ananya
Enter your password :(minimum 4 characters, max 10 characters) 
6789

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
1

Enter mail id of receiver : 
b@ccoew.
Account not found!


*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
1

Enter mail id of receiver : 
b@ccoew.in
Enter your message : 
hi C here


*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
2

No messages yet!


*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
3

To => b@ccoew.in 08-11-2023  23:07:54
Message : hi C here



*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
5

No messages yet!!

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
0

Do you want to sign in another account?
Enter 1 for YES
1

Enter mail id: 
b@ccoew.in
You are an existing user!
Enter your password : 
3456

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
2

1 : From => c@ccoew.in 08-11-2023  23:07:54
Message : hi C here



*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
5

1 : From => c@ccoew.in 08-11-2023  23:07:54
Message : hi C here


Enter index of the mail you want to forward : 
3
Index does not exist !! 
Enter option 5 again

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
5

1 : From => c@ccoew.in 08-11-2023  23:07:54
Message : hi C here


Enter index of the mail you want to forward : 
1
Enter the email address of the recipient to whom you'd like to forward this email : 
a@ccoew.in

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
3

To => a@ccoew.in 08-11-2023  23:07:54
Message : hi B here

To => a@ccoew.in 08-11-2023  23:07:54
Message : 1 : From => c@ccoew.in 08-11-2023  23:07:54
Message : hi C here



*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
4

1 : From => c@ccoew.in 08-11-2023  23:07:54
Message : hi C here

Enter index of the message you want to delete :
1
Message with index 1 deleted from inbox.

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
2

No messages yet!


*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
0

Do you want to sign in another account?
Enter 1 for YES
1

Enter mail id: 
c@ccoew.in
You are an existing user!
Enter your password : 
6789

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
2

No messages yet!


*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
3

To => b@ccoew.in 08-11-2023  23:07:54
Message : hi C here



*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
0

Do you want to sign in another account?
Enter 1 for YES
1

Enter mail id: 
a@ccoew.in
You are an existing user!
Enter your password : 
1234

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
2

1 : From => b@ccoew.in 08-11-2023  23:07:54
Message : hi B here

2 : From => b@ccoew.in 08-11-2023  23:07:54
Message : 1 : From => c@ccoew.in 08-11-2023  23:07:54
Message : hi C here



*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
6

1 : From => b@ccoew.in 08-11-2023  23:07:54
Message : hi B here

2 : From => b@ccoew.in 08-11-2023  23:07:54
Message : 1 : From => c@ccoew.in 08-11-2023  23:07:54
Message : hi C here

Enter index of message to be starred
1
STAREED1 : From => b@ccoew.in 08-11-2023  23:07:54
Message : hi B here
Message with index 1 is Starred

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
7

Displaying starred messages ==>
1 : From => b@ccoew.in 08-11-2023  23:07:54
Message : hi B here

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
1

Enter mail id of receiver : 
a@ccoew.in
Enter your message : 
hi a here again


*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
2

1 : From => b@ccoew.in 08-11-2023  23:07:54
Message : hi B here

2 : From => b@ccoew.in 08-11-2023  23:07:54
Message : 1 : From => c@ccoew.in 08-11-2023  23:07:54
Message : hi C here

3 : From => a@ccoew.in 08-11-2023  23:07:54
Message : hi a here again



*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
3

To => a@ccoew.in 08-11-2023  23:07:54
Message : hi a here again



*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
5

1 : From => b@ccoew.in 08-11-2023  23:07:54
Message : hi B here

2 : From => b@ccoew.in 08-11-2023  23:07:54
Message : 1 : From => c@ccoew.in 08-11-2023  23:07:54
Message : hi C here

3 : From => a@ccoew.in 08-11-2023  23:07:54
Message : hi a here again


Enter index of the mail you want to forward : 
3
Enter the email address of the recipient to whom you'd like to forward this email : 
b@ccoew.in

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
6

1 : From => b@ccoew.in 08-11-2023  23:07:54
Message : hi B here

2 : From => b@ccoew.in 08-11-2023  23:07:54
Message : 1 : From => c@ccoew.in 08-11-2023  23:07:54
Message : hi C here

3 : From => a@ccoew.in 08-11-2023  23:07:54
Message : hi a here again

Enter index of message to be starred
3
STAREED3 : From => a@ccoew.in 08-11-2023  23:07:54
Message : hi a here again
Message with index 3 is Starred

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
7

Displaying starred messages ==>
1 : From => b@ccoew.in 08-11-2023  23:07:54
Message : hi B here
3 : From => a@ccoew.in 08-11-2023  23:07:54
Message : hi a here again

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
8

Are you sure you want to permanently delete your account?
Enter 1 for Yes, 0 for No: 
1
please Re-Enter your E-mail ID to confirm deletion: 
a@ccoew.in
Account deleted successfully.

Do you want to sign in another account?
Enter 1 for YES
1

Enter mail id: 
b2ccoew.in
Invalid E-mail ID!
Make sure it ends with @ccoew.in
Enter mail id: 
b@ccoew.in
You are an existing user!
Enter your password : 
3456

*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
2

1 : From => a@ccoew.in 08-11-2023  23:07:54
Message : 3 : From => a@ccoew.in 08-11-2023  23:07:54
Message : hi a here again



*****************************************************************
MENU
0.Log out
1.Draft a mail.
2.Display Inbox
3.Display Outbox
4.Delete a particular message
5.Forward a email
6.Star a message
7.Display Starred messages
8.Delete your account
Enter choice : 
3

To => a@ccoew.in 08-11-2023  23:07:54
Message : hi B here

To => a@ccoew.in 08-11-2023  23:07:54
Message : 1 : From => c@ccoew.in 08-11-2023  23:07:54
Message : hi C here

		 */