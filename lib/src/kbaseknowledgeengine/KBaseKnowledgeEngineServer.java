package kbaseknowledgeengine;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;

//BEGIN_HEADER
import java.net.URL;
//END_HEADER

/**
 * <p>Original spec-file module name: KBaseKnowledgeEngine</p>
 * <pre>
 * A KBase module: KBaseKnowledgeEngine
 * </pre>
 */
public class KBaseKnowledgeEngineServer extends JsonServerServlet {
    private static final long serialVersionUID = 1L;
    private static final String version = "0.0.1";
    private static final String gitUrl = "https://github.com/rsutormin/KBaseKnowledgeEngine";
    private static final String gitCommitHash = "14500a76668db6c73f38944d3890ee1263a57dcd";

    //BEGIN_CLASS_HEADER
    IKBaseKnowledgeEngine impl = null;  //new FakeKBaseKnowledgeEngine();
    //END_CLASS_HEADER

    public KBaseKnowledgeEngineServer() throws Exception {
        super("KBaseKnowledgeEngine");
        //BEGIN_CONSTRUCTOR
        String mongoHosts = config.get("mongo-hosts");
        String mongoDb = config.get("mongo-db");
        String mongoUser = config.get("mongo-user");
        String mongoPassword = config.get("mongo-password");
        URL executionEngineUrl = new URL(config.get("njsw-url"));
        String admins = config.get("admins");
        impl = new DBKBaseKnowledgeEngine(mongoHosts, mongoDb, mongoUser, mongoPassword, 
                executionEngineUrl, admins, config);
        //END_CONSTRUCTOR
    }

    /**
     * <p>Original spec-file function name: getConnectorsStatus</p>
     * <pre>
     * </pre>
     * @return   instance of list of type {@link kbaseknowledgeengine.ConnectorStatus ConnectorStatus}
     */
    @JsonServerMethod(rpc = "KBaseKnowledgeEngine.getConnectorsStatus", async=true)
    public List<ConnectorStatus> getConnectorsStatus(AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        List<ConnectorStatus> returnVal = null;
        //BEGIN getConnectorsStatus
        returnVal = impl.getConnectorsStatus(authPart, jsonRpcContext);
        //END getConnectorsStatus
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: getAppsStatus</p>
     * <pre>
     * </pre>
     * @return   instance of list of type {@link kbaseknowledgeengine.AppStatus AppStatus}
     */
    @JsonServerMethod(rpc = "KBaseKnowledgeEngine.getAppsStatus", async=true)
    public List<AppStatus> getAppsStatus(AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        List<AppStatus> returnVal = null;
        //BEGIN getAppsStatus
        returnVal = impl.getAppsStatus(authPart, jsonRpcContext);
        //END getAppsStatus
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: runApp</p>
     * <pre>
     * Execute KE-App.
     * </pre>
     * @param   params   instance of type {@link kbaseknowledgeengine.RunAppParams RunAppParams}
     * @return   instance of type {@link kbaseknowledgeengine.RunAppOutput RunAppOutput}
     */
    @JsonServerMethod(rpc = "KBaseKnowledgeEngine.runApp", async=true)
    public RunAppOutput runApp(RunAppParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        RunAppOutput returnVal = null;
        //BEGIN runApp
        returnVal = impl.runApp(params, authPart, jsonRpcContext);
        //END runApp
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: testInit</p>
     * <pre>
     * Restores the initial state (for testing)
     * </pre>
     */
    @JsonServerMethod(rpc = "KBaseKnowledgeEngine.testInit", async=true)
    public void testInit(AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        //BEGIN testInit
    	impl.testInit(authPart, jsonRpcContext);
        //END testInit
    }
    @JsonServerMethod(rpc = "KBaseKnowledgeEngine.status")
    public Map<String, Object> status() {
        Map<String, Object> returnVal = null;
        //BEGIN_STATUS
        returnVal = new LinkedHashMap<String, Object>();
        returnVal.put("state", "OK");
        returnVal.put("message", "");
        returnVal.put("version", version);
        returnVal.put("git_url", gitUrl);
        returnVal.put("git_commit_hash", gitCommitHash);
        //END_STATUS
        return returnVal;
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            new KBaseKnowledgeEngineServer().startupServer(Integer.parseInt(args[0]));
        } else if (args.length == 3) {
            JsonServerSyslog.setStaticUseSyslog(false);
            JsonServerSyslog.setStaticMlogFile(args[1] + ".log");
            new KBaseKnowledgeEngineServer().processRpcCall(new File(args[0]), new File(args[1]), args[2]);
        } else {
            System.out.println("Usage: <program> <server_port>");
            System.out.println("   or: <program> <context_json_file> <output_json_file> <token>");
            return;
        }
    }
}
