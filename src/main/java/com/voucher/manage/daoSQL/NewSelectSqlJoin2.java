package com.voucher.manage.daoSQL;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.voucher.manage.daoSQL.annotations.DBTable;
import com.voucher.manage.daoSQL.annotations.QualifiLimit;
import com.voucher.manage.daoSQL.annotations.QualifiNotIn;
import com.voucher.manage.daoSQL.annotations.QualifiOffset;
import com.voucher.manage.daoSQL.annotations.QualifiOrder;
import com.voucher.manage.daoSQL.annotations.QualifiSort;
import com.voucher.manage.daoSQL.annotations.QualifiWhere;
import com.voucher.manage.daoSQL.annotations.QualifiWhereTerm;
import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLDouble;
import com.voucher.manage.daoSQL.annotations.SQLFloat;
import com.voucher.manage.daoSQL.annotations.SQLInteger;
import com.voucher.manage.daoSQL.annotations.SQLLong;
import com.voucher.manage.daoSQL.annotations.SQLString;
import com.voucher.manage.tools.MyTestUtil;

public class NewSelectSqlJoin2 {
	
	public static Map<String, Object> get(Object[] objects,String[][] joinParameters,String[] itemjoinParameters) throws ClassNotFoundException{
        Integer limit=10;
		Integer offset=0; 
		String notIn="";
    	String sort=null;
		String order=null;
        String tableName="";
        String tableName0="";
        String defaultTable="";
        String firstTableName="";
        String select="";
        List<String> columnDefs = new ArrayList<String>();
        String[] columnWhere=null;
        List wheres=new ArrayList<String[]>();
        boolean term=false;       //判断是否有where
        String leftJionTableName="";
        String Term="AND";
        
        Map<String, Object> map=new HashMap<>();
        
        List<Object> params=new ArrayList<Object>();
        
        int i=0;

        for(Object object:objects){
    		Class className=object.getClass();
       	    String name = className.getName();                                    //从控制台输入一个类名，我们输入User即可
            Class<?> cl = Class.forName(name);                         //加载类，如果该类不在默认路径底下，会报 java.lang.ClassNotFoundException
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            
            tableName = (dbTable.name().length()<1)?cl.getName():dbTable.name();//获取表的名字，如果没有在DBTable中定义，则获取类名作为Table的名字
            
            defaultTable=tableName;   //把第一张表设为默认表,用于定义默认order的表的值
            break;
            
        }
    
      int s=0;  
	  for(Object object:objects){
		Class className=object.getClass();
   	    String name = className.getName();                                    //从控制台输入一个类名，我们输入User即可
        Class<?> cl = Class.forName(name);                         //加载类，如果该类不在默认路径底下，会报 java.lang.ClassNotFoundException
        DBTable dbTable = cl.getAnnotation(DBTable.class);         //从User类中获取DBTable注解
        try{
         tableName = (dbTable.name().length()<1)?cl.getName():dbTable.name();//获取表的名字，如果没有在DBTable中定义，则获取类名作为Table的名字

         if(i==0){
          firstTableName=tableName;
          tableName0=firstTableName;
         }else{  
          String[] joinParameter=joinParameters[s];
          leftJionTableName=leftJionTableName+" left join "+tableName+" on " +defaultTable+"."+joinParameter[0]
        		  +"="+tableName+"."+joinParameter[1];
          tableName0=tableName;
          s++;
         }
        }catch (Exception e) {
			// TODO: handle exception
         tableName =name;
		 }
       
         
        for(Field field : cl.getDeclaredFields())                  //获取声明的属性
        {
            String columnName = null;           
            Annotation[] anns = field.getDeclaredAnnotations();//获取注解，一个属性可以有多个注解，所以是数组类型
            if(anns.length < 1)
            {
                continue;
            }else
            if(anns[0] instanceof SQLInteger)                //判断注解类型
            {
                SQLInteger sInt = (SQLInteger)anns[0];
                columnName = tableName+"."+((sInt.name().length()<1)?field.getName():sInt.name());//获取列名称与获取表名一样
                columnDefs.add(columnName);//使用一个方法，自己写的getConstraints(Constraints constraints)获取列定义
            }else
            if(anns[0] instanceof SQLString)
            {
                SQLString sStr = (SQLString)anns[0];
                columnName = tableName+"."+((sStr.name().length()<1)?field.getName().toUpperCase():sStr.name());
                columnDefs.add(columnName);
            }else
            if(anns[0] instanceof SQLFloat)
            {
                SQLFloat sStr = (SQLFloat) anns[0];
                columnName = tableName+"."+((sStr.name().length()<1)?field.getName().toUpperCase():sStr.name());
                columnDefs.add(columnName);
            }else
             if(anns[0] instanceof SQLDouble)
             {
                 SQLDouble sStr =  (SQLDouble) anns[0];
                 columnName = tableName+"."+((sStr.name().length()<1)?field.getName().toUpperCase():sStr.name());
                 columnDefs.add(columnName);
              }else
             if(anns[0] instanceof SQLLong)
             {
                  SQLLong sStr = (SQLLong) anns[0];
                  columnName = tableName+"."+((sStr.name().length()<1)?field.getName().toUpperCase():sStr.name());
                  columnDefs.add(columnName);
             }else
            if(anns[0] instanceof SQLDateTime)
            {
                SQLDateTime sStr = (SQLDateTime) anns[0];
                columnName = tableName+"."+((sStr.name().length()<1)?field.getName().toUpperCase():sStr.name());
                columnDefs.add(columnName);
            }else
            if(anns[0] instanceof QualifiWhere)
            {            	
                QualifiWhere sStr = (QualifiWhere) anns[0];
                columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
                columnWhere = AReflectGet.getArrayMethods(object, className, field, columnName);
             //   System.out.println("columnWhere="+columnWhere);
                if(columnWhere!=null){
                	wheres.add(columnWhere);
                	term=true;
                }
            }else
            if(anns[0] instanceof QualifiWhereTerm)
            {            	
                QualifiWhereTerm sStr = (QualifiWhereTerm) anns[0];
                columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
                String current= AReflectGet.getStringMethods(object, className, field, columnName);
              //  System.out.println("columnWhere="+columnWhere);
                 if(current!=null){
                      Term=current;
                    }
            }else
            if(anns[0] instanceof QualifiLimit)
            {
            	 QualifiLimit sStr = (QualifiLimit) anns[0];
                 columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
                 
                 String filedName = field.getName();  
                 limit=AReflectGet.getIntMethods(object, className, field,columnName);
             }else	
            if(anns[0] instanceof QualifiOffset)
            {
            	QualifiOffset sStr = (QualifiOffset) anns[0];
                columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
                offset=AReflectGet.getIntMethods(object, className, field,columnName);                
            }else
             if(anns[0] instanceof QualifiNotIn)
             {
            	QualifiNotIn sStr = (QualifiNotIn) anns[0];
                columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
                notIn=AReflectGet.getStringMethods(object, className, field,columnName);
             }else	
            if(anns[0] instanceof QualifiSort)
            {
            	QualifiSort sStr = (QualifiSort) anns[0];
                columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
                String sor=AReflectGet.getStringMethods(object, className, field,columnName);
                if(sor!=null)
                	sort=defaultTable+"."+sor;  //避免多表造成混乱,以第一张表为主排序
             }else
            if(anns[0] instanceof QualifiOrder)
            {
            	QualifiOrder sStr = (QualifiOrder) anns[0];
                columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
                String orde=AReflectGet.getStringMethods(object, className, field,columnName);
                if(orde!=null)
                	order=orde;
             }
        }
        
        StringBuilder selectCommand = new StringBuilder("SELECT top "+limit);
                
        for(String columnDef :columnDefs){
            selectCommand.append("\n    "+columnDef+",");
        }
        
        select=selectCommand.substring(0,selectCommand.length()-1)+"\n FROM \n   "+firstTableName+leftJionTableName;
        
        String tableName2="[item_"+tableName.substring(1, tableName.length());
        
        try{
            tableName = (dbTable.name().length()<1)?cl.getName():dbTable.name();//获取表的名字，如果没有在DBTable中定义，则获取类名作为Table的名字

            if(i==0){
             firstTableName=tableName;
             tableName0=firstTableName;
            }else{  
             String joinParameter=itemjoinParameters[s];
             leftJionTableName=leftJionTableName+" left join "+tableName2+" on " +defaultTable+"."+joinParameter
              		  +"="+tableName2+"."+joinParameter;
             tableName0=tableName;
             s++;
            }
           }catch (Exception e) {
   			// TODO: handle exception
            tableName =name;
   		 }
        
   
        i++;
   }

 
     
      if(sort==null){
    	  sort=defaultTable+"."+notIn;  //sqlserver有版本分页存在bug,需要加上排序参数
      } 
	  
	  
	//  params.add(0,limit); //把limit插入到最前面
  
      int r=0;
      
	  if(term){
          StringBuilder whereCommand = new StringBuilder();         
          Iterator<String[]> iterator=wheres.iterator();
          MyTestUtil.print(wheres);
         
        	  columnWhere=iterator.next();
        	  int k1=1;
        	  /*
        	  for(String whereterm:columnWhere){
            	  if(k1%2==0){
            		//  System.out.println("偶数");
            		//  whereCommand.append(whereterm+"\n  AND ");
            	   }else{
            		//  System.out.println("奇数");
            		//  whereCommand.append("\n   "+whereterm);
            		   whereCommand.append(whereterm+"? \n  "+Term+" ");
            	   }
               k1++;
               System.out.println("whereCommand="+whereCommand);
               System.out.println("r="+r);
               r++;
              }
		  */

  			int ii=0;
  			boolean aTerm=false;
  			boolean bTerm=false;
  			boolean cTerm=false;
  			String awhere=null;
  			String bwhere=null;
  			String cwhere=null;

  			for(String str : columnWhere){

  				System.out.println("str="+str);

  				String REGEX = "del=";
  				Pattern pattern=Pattern.compile(REGEX);
  				Matcher matcher=pattern.matcher(str);
  				if(matcher.find()){
  					awhere=" "+str+" ";
  					aTerm=true;
  					continue;
  				}

  				if(aTerm){
  					awhere=awhere+" ? ";
  			    	aTerm=false;
  			    	continue;
  				}
	
  				String REGEX1 = "region=";
  				Pattern pattern1=Pattern.compile(REGEX1);
  				Matcher matcher1=pattern1.matcher(str);				

  				if(matcher1.find()){
  					bwhere=" "+str+" ";
  					bTerm=true;
  					continue;
  				}
  				System.out.println("bwhere="+bwhere);
  				if(bTerm){
  					bwhere=bwhere+" ? ";
  			    	bTerm=false;
  			    	continue;
  				}
  				
  				String REGEX2 = "weight=";
  				Pattern pattern2=Pattern.compile(REGEX2);
  				Matcher matcher2=pattern2.matcher(str);				

  				if(matcher2.find()){
  					cwhere=" "+str+" ";
  					cTerm=true;
  					continue;
  				}
  				System.out.println("cwhere="+cwhere);
  				if(cTerm){
  					cwhere=cwhere+" ? ";
  			    	cTerm=false;
  			    	continue;
  				}

  			    if(ii%2==0){
  			    	whereCommand.append(str);
  			    }else{
  			    	whereCommand.append(" ? ");
  			    	whereCommand.append(" "+Term+" ");
  			    }
  			    ii++;
  			}
  			String ss = whereCommand.toString();

  			String serach = null;

  			if(ss!=null&&!ss.equals("")){
  				serach="("+ss.substring(0,ss.length()-4)+")";
  			}

  			if(serach!=null){
  				if((awhere!=null&&bwhere!=null)){
  					serach=serach+" and ("+awhere+" and "+bwhere+")";
  				}else if(awhere!=null&&bwhere==null){
  					serach=serach+" and ("+awhere+")";
  				}else if(awhere==null&&bwhere!=null){
  					serach=serach+" and ("+bwhere+")";
  				}
  			}else{
  				if((awhere!=null&&bwhere!=null)){
  					serach=" ("+awhere+" and "+bwhere+")";
  				}else if(awhere!=null&&bwhere==null){
  					serach="("+awhere+")";
  				}else if(awhere==null&&bwhere!=null){
  					serach="("+bwhere+")";
  				}
  			}
          
          select=select+   //sqlserver分页需要在top也加上where条件
         		 "\n  where "+firstTableName+"."+notIn+
                  " not in("+
                  " select top "+offset+" "+firstTableName+"."+notIn+" FROM "+firstTableName+leftJionTableName+" where "+
                   serach;

 
          
          if(sort!=null){
            	select=select+" ORDER BY "+sort;
              }
            
             if(order!=null&&sort!=null){       	
            	select=select+" "+order;        	
             }
           
           select=select+")";
           
       //   System.out.println("select="+select);
          i=1;
          int length=2;
          if(length>2){
        	  length=2;
          }
          iterator=wheres.iterator();
          System.out.println("wheres1="+wheres);
          int k2;
        
        	columnWhere=iterator.next();
        	System.out.println("length="+length);
            for(k2=1;k2<length;k2++){
             for(String whereterm:columnWhere){
        	   if(i%2==0){
        		//  System.out.println("偶数");
        		  params.add(whereterm);
        	    }else{
        		//  System.out.println("奇数");
        		 
        	    }
              i++;
             }
            }
         
          
          select=select+"\n  AND ("+serach+")";
       //   System.out.println("select="+select);
          i=1;
          iterator=wheres.iterator();
          System.out.println("wheres2="+wheres);
          
          int k3;
          
          columnWhere=iterator.next();
          System.out.println("length="+length);
          for(k3=1;k3<length;k3++){
            for(String whereterm:columnWhere){
        	  if(i%2==0){
        		//  System.out.println("偶数");
        		  params.add(whereterm);
        	   }else{
        		//  System.out.println("奇数");
        		 
        	   }
              i++;
           }
          }
         
          
        }else{
        	select=select+
           		 "\n  where "+firstTableName+"."+notIn+
                    " not in("+
                    " select top "+offset+" "+firstTableName+"."+notIn+" FROM "+firstTableName+leftJionTableName;
        	
        	if(sort!=null){
              	select=select+" ORDER BY "+sort;
                }
              
               if(order!=null&&sort!=null){       	
              	select=select+" "+order;        	
               }
        	
        	select=select+")";
        }
	  	 
      
	  if(sort!=null){
        	select=select+" ORDER BY "+sort;
          }
        
         if(order!=null&&sort!=null){       	
        	select=select+" "+order;        	
         }
      
	  map.put("sql", select);
      map.put("params", params);
      return map;
   }
	
	
	public static Map<String, Object> getCount(Object[] objects,String[][] joinParameters) throws ClassNotFoundException{
		List<String> columnDefs = new ArrayList<String>();
        String[] columnWhere=null;
        List wheres=new ArrayList<String[]>();
        String tableName="";
        String tableName0="";
        String firstTableName="";
        String defaultTable="";
        String select="";
        boolean term=false;       //判断是否有where
        String leftJionTableName="";
        List<Object> params=new ArrayList<Object>();
        String Term="AND";
        
        Map<String, Object> map=new HashMap<>();
        
        for(Object object:objects){
    		Class className=object.getClass();
       	    String name = className.getName();                                    //从控制台输入一个类名，我们输入User即可
            Class<?> cl = Class.forName(name);                         //加载类，如果该类不在默认路径底下，会报 java.lang.ClassNotFoundException
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            
            tableName = (dbTable.name().length()<1)?cl.getName():dbTable.name();//获取表的名字，如果没有在DBTable中定义，则获取类名作为Table的名字
            
            defaultTable=tableName;   //把第一张表设为默认表,用于定义默认order的表的值
            break;
            
        }
        
        int i=0;
        
        int s=0;
        

		for(Object object:objects){
			Class className=object.getClass();
	   	    String name = className.getName();                                    //从控制台输入一个类名，我们输入User即可
	        Class<?> cl = Class.forName(name);                         //加载类，如果该类不在默认路径底下，会报 java.lang.ClassNotFoundException
	        DBTable dbTable = cl.getAnnotation(DBTable.class);         //从User类中获取DBTable注解
	        try{
	         tableName = (dbTable.name().length()<1)?cl.getName():dbTable.name();//获取表的名字，如果没有在DBTable中定义，则获取类名作为Table的名字

	         if(i==0){
	          firstTableName=tableName;
	          tableName0=firstTableName;
	         }else{    
	          String[] joinParameter=joinParameters[s];
	          leftJionTableName=leftJionTableName+" left join "+tableName+" on " +defaultTable+"."+joinParameter[0]
	        		  +"="+tableName+"."+joinParameter[1];
	          tableName0=tableName;
	          s++;
	         }
	        }catch (Exception e) {
				// TODO: handle exception
	         tableName =name;
			 }
	       
	         
	         int j=0;
	        for(Field field : cl.getDeclaredFields())                  //获取声明的属性
	        {

	            String columnName = null;           
	            Annotation[] anns = field.getDeclaredAnnotations();//获取注解，一个属性可以有多个注解，所以是数组类型
	            if(anns.length < 1)
	            {
	                continue;
	            }else	            
	            if(anns[0] instanceof QualifiWhere)
	            {            	
	                QualifiWhere sStr = (QualifiWhere) anns[0];
	                columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
	                columnWhere = AReflectGet.getArrayMethods(object, className, field, columnName);
	                System.out.println("columnWhere="+columnWhere);
	                if(columnWhere!=null){
	                	wheres.add(columnWhere);
	                	term=true;
	                }
	            }else
		        if(anns[0] instanceof QualifiWhereTerm)
		         {            	
		             QualifiWhereTerm sStr = (QualifiWhereTerm) anns[0];
		             columnName = (sStr.name().length()<1)?field.getName().toUpperCase():sStr.name();
		             String current= AReflectGet.getStringMethods(object, className, field, columnName);
		         //    System.out.println("columnWhere="+columnWhere);
		              if(current!=null){
		                    Term=current;
		               }
		          }
	        }
	        
	        StringBuilder selectCommand = new StringBuilder("SELECT count(*)");
	                
	        for(String columnDef :columnDefs){
	            selectCommand.append("\n    "+columnDef+",");
	        }
	        
	        select=selectCommand+"\n FROM \n   "+firstTableName+leftJionTableName;
	        
	   
	        i++;
	   }
	        
	       		     
		if(term){
	          StringBuilder whereCommand = new StringBuilder();         
	          Iterator<String[]> iterator=wheres.iterator();
	          System.out.println("wheres="+wheres);
	          
	        	  columnWhere=iterator.next();

	        	  /*
	        	  int k1=1;
	        	  for(String whereterm:columnWhere){
	            	  if(k1%2==0){
	            		//  System.out.println("偶数");
	            		//  whereCommand.append(whereterm+"\n  AND ");
	            	   }else{
	            		//  System.out.println("奇数");
	            		//  whereCommand.append("\n   "+whereterm);
	            		   whereCommand.append(whereterm+"? \n  "+Term+" ");
	            	   }
	               k1++;
	               System.out.println("whereCommand="+whereCommand);
	              }
	              */

	        	  int ii=0;
	    			boolean aTerm=false;
	    			boolean bTerm=false;
	    			boolean cTerm=false;
	    			String awhere=null;
	    			String bwhere=null;
	    			String cwhere=null;

	    			for(String str : columnWhere){

	    				System.out.println("str="+str);

	    				String REGEX = "del=";
	    				Pattern pattern=Pattern.compile(REGEX);
	    				Matcher matcher=pattern.matcher(str);
	    				if(matcher.find()){
	    					awhere=" "+str+" ";
	    					aTerm=true;
	    					continue;
	    				}

	    				if(aTerm){
	    					awhere=awhere+" ? ";
	    			    	aTerm=false;
	    			    	continue;
	    				}

	    				String REGEX1 = "region=";
	    				Pattern pattern1=Pattern.compile(REGEX1);
	    				Matcher matcher1=pattern1.matcher(str);
	    				if(matcher1.find()){
	    					bwhere=" "+str+" ";
	    					bTerm=true;
	    					continue;
	    				}
	    				System.out.println("bwhere="+bwhere);
	    				if(bTerm){
	    					bwhere=bwhere+" ? ";
	    			    	bTerm=false;
	    			    	continue;
	    				}

	    				String REGEX2 = "weight=";
	    				Pattern pattern2=Pattern.compile(REGEX2);
	    				Matcher matcher2=pattern2.matcher(str);

	    				if(matcher2.find()){
	    					cwhere=" "+str+" ";
	    					cTerm=true;
	    					continue;
	    				}
	    				System.out.println("cwhere="+cwhere);
	    				if(cTerm){
	    					cwhere=cwhere+" ? ";
	    			    	cTerm=false;
	    			    	continue;
	    				}
	    				
	    			    if(ii%2==0){
	    			    	whereCommand.append(str);
	    			    }else{
	    			    	whereCommand.append(" ? ");
	    			    	whereCommand.append(" "+Term+" ");
	    			    }
	    			    ii++;
	    			}
	    			String ss = whereCommand.toString();

	    			String serach = null;

	    			if(ss!=null&&!ss.equals("")){
	    				serach="("+ss.substring(0,ss.length()-4)+")";
	    			}

	    			if(serach!=null){
	    				if((awhere!=null&&bwhere!=null)){
	    					serach=serach+" and ("+awhere+" and "+bwhere+")";
	    				}else if(awhere!=null&&bwhere==null){
	    					serach=serach+" and ("+awhere+")";
	    				}else if(awhere==null&&bwhere!=null){
	    					serach=serach+" and ("+bwhere+")";
	    				}
	    			}else{
	    				if((awhere!=null&&bwhere!=null)){
	    					serach=" ("+awhere+" and "+bwhere+")";
	    				}else if(awhere!=null&&bwhere==null){
	    					serach="("+awhere+")";
	    				}else if(awhere==null&&bwhere!=null){
	    					serach="("+bwhere+")";
	    				}
	    			}

	          select=select+   //sqlserver分页需要在top也加上where条件
	          		 "\n  where "+serach;
	          
	       iterator=wheres.iterator();
	       
	        	  columnWhere=iterator.next();
	        	  int k2=1;
	        	  for(String whereterm:columnWhere){
	            	  if(k2%2==0){
	            		//  System.out.println("偶数");
	            		  params.add(whereterm);
	            	   }else{
	            		//  System.out.println("奇数");
	            		//  whereCommand.append("\n   "+whereterm);
	            	   }
	               k2++;
	              }
			  
	          
	        }
		
		 map.put("sql", select);
         map.put("params", params);
             
       return map;
	 }
	
}
