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
        // ����������
        String driver = "com.mysql.jdbc.Driver";
        // URLָ��Ҫ���ʵ����ݿ���world
        String url = "jdbc:mysql://119.28.187.194:3306/infosys?characterEncoding=utf-8";
        // MySQL����ʱ���û���
        String user = "root";
        // MySQL����ʱ������
        String password = "HelloWorld1!";
        try {
            // ������������
            Class.forName(driver);
            // �������ݿ�
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
        //��ȡ����
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from STUDENT;");
            rs.last();
            int row=rs.getRow(); int col=rs.getMetaData().getColumnCount();
            Object[][] data=new Object[row+1][col];
            int i,j=0;
            rs.beforeFirst();
            while(rs.next())  {
                //ѡ��Name��������
                for(i=1;i<col+1;i++) {
                    data[j][i-1]=rs.getObject(i);
                }
                j++;
            }
            //��ӡ����
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
