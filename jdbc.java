class getdata extends  Thread{
    Connection conn;
    @Override
    public void run() {
        try {
            this.DB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void DB() throws SQLException {
        // 驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        // URL指向要访问的数据库名world
        String url = "jdbc:mysql://119.28.187.194:3306/infosys?characterEncoding=utf-8";
        // MySQL配置时的用户名
        String user = "root";
        // MySQL配置时的密码
        String password = "HelloWorld1!";
        try {
            // 加载驱动程序
            Class.forName(driver);
            // 连续数据库
            conn = DriverManager.getConnection(url, user, password);
            if(!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");
        }
        catch(ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

        Statement statement = null;
        //获取数据
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from STUDENT;");
            rs.last();
            int row=rs.getRow(); int col=rs.getMetaData().getColumnCount();
            Object[][] data=new Object[row+1][col];
            int i,j=0;
            rs.beforeFirst();
            while(rs.next())  {
                //选择Name这列数据
                for(i=1;i<col+1;i++) {
                    data[j][i-1]=rs.getObject(i);
                }
                j++;
            }
            //打印数据
            for(i=0;i<row;i++)
            {
                for(j=0;j<col;j++)
                {
                    System.out.print(data[i][j]+" | ");
                }
                System.out.println("\n");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
