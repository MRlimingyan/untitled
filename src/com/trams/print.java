package com.trams;

import org.junit.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class print {
    Scanner scanner = new Scanner(System.in);
    ArrayList<tram> list = new ArrayList();
    HashMap<String, fixRecords> records = new HashMap();

    public void mainPrint() {
        try {
            loadData();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        do {
            System.out.println("-------欢迎浏览电车管理系统-------");
            System.out.println("\t\t\t1添加电车");
            System.out.println("\t\t\t2删除电车");
            System.out.println("\t\t\t3修改状态");
            System.out.println("\t\t\t4查询电车");
            System.out.println("\t\t\t5添加维护记录");
            System.out.println("\t\t\t6查看维护记录");
            System.out.println("\t\t\t7保存数据");
            System.out.println("\t\t\t8退出系统");
            try {
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        addTram();
                        break;
                    case 2:
                        deleteTram();
                        break;
                    case 3:
                        modifyTram();
                        break;
                    case 4:
                        queryTram();
                        break;
                    case 5:
                        addMaintainRecord();
                        break;
                    case 6:
                        queryMaintainRrcord();
                        break;
                    case 7:
                        saveData();
                        break;
                    case 9:
                        printAll();
                        break;
                    case 10:
                        deletAll();
                        break;
                    case 8:
                        System.exit(0);
                    default:
                        System.out.println("输入有误请重新输入");
                }
            } catch (Exception e) {
                System.out.println("请输入数字");
                scanner.nextLine();
            }
        }
        while (true);
    }
    public void addTram() {
        System.out.println("编号：");
        String id = scanner.next();
        for (tram t : list) {
            if (id.equals(t.getId())) {
                System.out.println("该编号已有列车占用，请重新输入");
                return;
            }
        }
        System.out.println("类型：");
        String model = scanner.next();
        System.out.println("状态：1运行 2维修 3停运，请输入对应的数字编号");
        String status = scanner.next();
        switch (status) {
            case "1":
                status = "运行";
                break;
            case "2":
                status = "维修";
                break;
            case "3":
                status = "停运";
                break;
            default:
                System.out.println("输入有误请重新输入");
                return;
        }
        System.out.println("线路：");
        String line = scanner.next();
        tram tram = new tram(model, id, status, line);
        list.add(tram);
    }

    public void deleteTram() {
        System.out.println("输入删除列车编号：");
        String id = scanner.next();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                list.remove(i);
                System.out.println("该车已不存在");
                break;
            }
        }
    }

    public void modifyTram() {
        System.out.println("输入修改列车编号：");
        String id = scanner.next();
        for (com.trams.tram tram : list) {
            if (tram.getId().equals(id)) {
                System.out.println("修改类型为：");
                String model = scanner.next();
                tram.setModel(model);
                System.out.println("修改状态为：");
                String status = scanner.next();
                switch (status) {
                    case "1":
                        status = "运行";
                        break;
                    case "2":
                        status = "维修";
                        break;
                    case "3":
                        status = "停运";
                        break;
                    default:
                        System.out.println("输入有误请重新输入");
                        return;
                }
                tram.setStatus(status);
                System.out.println("修改线路为：");
                String line = scanner.next();
                tram.setLine(line);
                System.out.println("已全部修改完毕");
                break;
            }
        }
    }

    public void queryTram() {
        System.out.println("请输入查询的列车编号");
        String id = scanner.next();
        int count = 0;
        for (com.trams.tram tram : list) {
            if (tram.getId().equals(id)) {
                System.out.println(tram);
                count++;
                break;
            }

        }
        if (count == 0) {
            System.out.println("未找到该列车数据");
        }
    }

    public void addMaintainRecord() {
        LocalDate localDate = null;

        System.out.println("请输入维护日期：");
        try {
            System.out.println("请输入年份：");
            int year = scanner.nextInt();
            System.out.println("请输入月份：");
            int month = scanner.nextInt();
            System.out.println("请输入日期：");
            int day = scanner.nextInt();
            localDate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            System.out.println("输入错误，请检查输入的年、月、日信息是否有效。");
        } finally {

        }
        System.out.println("请输入维护类型：");
        String type = scanner.next();
        System.out.println("请输入维护人员：");
        String staff = scanner.next();
        System.out.println("请输入关联电车编号：");
        String tramId = scanner.next();
        fixRecords fixRecords = new fixRecords(localDate, type, staff, tramId);
        records.put(tramId, fixRecords);
        System.out.println("已完成添加");
    }

    public void queryMaintainRrcord() {
        System.out.println("请输入查询的电车编号（维护查询）");
        int count = 0;
        String id = scanner.next();
        for (String key : records.keySet()) {
            if (key.equals(id)) {
                System.out.println(records.get(key));
                count++;
                break;
            }
        }
        if (count == 0) {
            System.out.println("未找到该数据");
        }
    }

    public void loadData() throws ClassNotFoundException, RuntimeException {
        String filePath = "E:\\Trams\\trams.txt";
        String filePath1 = "E:\\Trams\\trams1.txt";
        File file = new File(filePath);
        File file1 = new File(filePath1);
        if (!file.exists()) {
            try {
               if(!(file.createNewFile())){
                   System.out.println("文件获取失败");
               }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!file1.exists()) {
            try {
                if(!(file1.createNewFile())){
                    System.out.println("文件获取失败");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ObjectInputStream ois = null;
        ObjectInputStream ois1 = null;
        if (file.length() != 0) {
            try {
                ois = new ObjectInputStream(new FileInputStream(filePath));
                ArrayList<tram> l = (ArrayList<tram>) ois.readObject();
                list = l;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
        if (file1.length() != 0) {
            try {

                ois1 = new ObjectInputStream(new FileInputStream(filePath1));
                HashMap<String, fixRecords> r = (HashMap<String, fixRecords>) ois1.readObject();
                records = r;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (ois1 != null) {
                    try {
                        ois1.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
    }

    public void saveData() {
        String filePath = "E:\\Trams\\trams.txt";
        String filePath1 = "E:\\Trams\\trams1.txt";
        ObjectOutputStream oos = null;
        ObjectOutputStream oos1 = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos1 = new ObjectOutputStream(new FileOutputStream(filePath1));
            oos.writeObject(list);
            oos1.writeObject(records);
            System.out.println("已成功保存");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos1 != null) {
                try {
                    oos1.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    public void printAll() {
        System.out.println("---所有电车数据为---");
        for (tram tram : list) {
            System.out.println(tram);
        }
        System.out.println("---所有电车维修数据为---");
        for (String key : records.keySet()) {
            System.out.println(records.get(key));
        }
    }

    public void deletAll() {
        list.clear();
        records.clear();
        System.out.println("已全部删除");
    }

}
