package com.mrfsong.el.util;

import javax.script.*;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * ${DESCRIPTION}
 * </P>
 *
 * @Author songfei20
 * @Date 2018/7/30 13:58
 * @Version 1.0
 */
public class ScriptUtils {
    /**
     * 脚本语言groovy
     */
    public static final String SCRIPT_GROOVY = "groovy";
    /**
     * 脚本语言javascript
     */
    public static final String SCRIPT_JAVASCRIPT = "javaScript";

    private static ScriptEngineManager engineManager = new ScriptEngineManager();
    /**
     * 脚本引擎缓存
     */
    private static Map<String, ScriptEngine> scriptEngineMap = new HashMap<>();

    /**
     * 获取脚本引擎
     *
     * @param scriptLang 脚本语言 (groovy, javaScript, etc.)
     * @return
     */
    public static ScriptEngine getEngine(String scriptLang) {
        ScriptEngine engine = scriptEngineMap.get(scriptLang);
        if (engine == null) {
            engine = engineManager.getEngineByName(scriptLang);
            scriptEngineMap.put(scriptLang, engine);
        }
        return engine;
    }

    /**
     * 执行脚本中的方法
     *
     * @param scriptLang 脚本语言
     * @param script 需要执行的脚本文本
     * @param bindings 给脚本设置的全局变量数据
     * @param functionName 执行的方法名
     * @return
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    public static Object invokeScriptFunction(String scriptLang, String script, String functionName,
                                              Map<String, Object> bindings)
            throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = getEngine(scriptLang);
        Bindings data = engine.createBindings();
        for (String key : bindings.keySet()) {
            data.put(key, bindings.get(key));
        }
        engine.eval(script, data);
        return ((Invocable) engine).invokeFunction(functionName);
    }

    /**
     * 执行脚本中的方法
     *
     * @param scriptLang 脚本语言
     * @param script 需要执行的脚本文本
     * @param args 执行脚本方法传入的参数
     * @param functionName 执行的方法名
     * @return
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    public static Object invokeScriptFunction(String scriptLang, String script, String functionName, Object... args)
            throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = getEngine(scriptLang);
        engine.eval(script);
        return ((Invocable) engine).invokeFunction(functionName, args);
    }

    /**
     * 执行脚本中的方法
     *
     * @param scriptLang 脚本语言
     * @param bindings 给脚本设置的全局变量数据
     * @param script 需要执行的脚本文本
     * @param args 执行脚本方法传入的参数
     * @param functionName 执行的方法名
     * @return
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    public static Object invokeScriptFunction(String scriptLang, Map<String, Object> bindings, String script,
                                              String functionName,
                                              Object... args) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = getEngine(scriptLang);
        Bindings data = engine.createBindings();
        for (String key : bindings.keySet()) {
            data.put(key, bindings.get(key));
        }
        engine.eval(script, data);
        return ((Invocable) engine).invokeFunction(functionName, args);
    }

    /**
     * 执行脚本中的方法
     *
     * @param scriptLang 脚本语言
     * @param reader 需要执行的脚本文件
     * @param bindings 给脚本设置的全局变量数据
     * @param functionName 执行的方法名
     * @return
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    public static Object invokeFileScriptFunction(String scriptLang, Reader reader, String functionName,
                                                  Map<String, Object> bindings)
            throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = getEngine(scriptLang);
        Bindings data = engine.createBindings();
        for (String key : bindings.keySet()) {
            data.put(key, bindings.get(key));
        }
        engine.eval(reader, data);
        return ((Invocable) engine).invokeFunction(functionName);
    }

    /**
     * 执行脚本中的方法
     *
     * @param scriptLang 脚本语言
     * @param reader 需要执行的脚本文件
     * @param args 执行脚本方法传入的参数
     * @param functionName 执行的方法名
     * @return
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    public static Object invokeFileScriptFunction(String scriptLang, Reader reader, String functionName, Object... args)
            throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = getEngine(scriptLang);
        engine.eval(reader);
        return ((Invocable) engine).invokeFunction(functionName, args);
    }

    /**
     * 执行脚本中的方法
     *
     * @param scriptLang 脚本语言
     * @param bindings 给脚本设置的全局变量数据
     * @param reader 需要执行的脚本文件
     * @param args 执行脚本方法传入的参数
     * @param functionName 执行的方法名
     * @return
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    public static Object invokeFileScriptFunction(String scriptLang, Map<String, Object> bindings, Reader reader,
                                                  String functionName,
                                                  Object... args) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = getEngine(scriptLang);
        Bindings data = engine.createBindings();
        for (String key : bindings.keySet()) {
            data.put(key, bindings.get(key));
        }
        engine.eval(reader, data);
        return ((Invocable) engine).invokeFunction(functionName, args);
    }
}
