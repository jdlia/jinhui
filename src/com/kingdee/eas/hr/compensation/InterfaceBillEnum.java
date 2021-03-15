/**
 * output package name
 */
package com.kingdee.eas.hr.compensation;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.StringEnum;

/**
 * output class name
 */
public class InterfaceBillEnum extends StringEnum
{
    public static final String NEW_VALUE = "1";//alias=新增
    public static final String SUBMIT_VALUE = "2";//alias=已提交
    public static final String AUDIT_VALUE = "3";//alias=已审核

    public static final InterfaceBillEnum NEW = new InterfaceBillEnum("NEW", NEW_VALUE);
    public static final InterfaceBillEnum SUBMIT = new InterfaceBillEnum("SUBMIT", SUBMIT_VALUE);
    public static final InterfaceBillEnum AUDIT = new InterfaceBillEnum("AUDIT", AUDIT_VALUE);

    /**
     * construct function
     * @param String interfaceBillEnum
     */
    private InterfaceBillEnum(String name, String interfaceBillEnum)
    {
        super(name, interfaceBillEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static InterfaceBillEnum getEnum(String interfaceBillEnum)
    {
        return (InterfaceBillEnum)getEnum(InterfaceBillEnum.class, interfaceBillEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(InterfaceBillEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(InterfaceBillEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(InterfaceBillEnum.class);
    }
}