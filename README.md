M Y D R I V E 

es16tg_23-project

$ mysql -p -u mydrive

Enter password: mydriv3

mysql> USE drivedb;

mysql> \q

$ git clone https://github.com/tecnico-softeng/es16tg_23-project.git

$ cd es16tg_23-project

$ mvn dbclean:dbclean clean package exec:java
