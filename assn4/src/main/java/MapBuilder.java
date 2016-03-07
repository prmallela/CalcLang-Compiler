import java.util.HashMap;

public class MapBuilder extends ConfigBaseVisitor<Void> {
    HashMap<String, String> map = new HashMap<>();
    String head;

    @Override
    public Void visitHead(ConfigParser.HeadContext ctx) {
        head = ctx.NAME().getText();
        return null;
    }

    @Override
    public Void visitVal(ConfigParser.ValContext ctx) {
        String varName = ctx.NAME(0).getText();
        String value = ctx.NAME(1).getText();
        String joined = head + '.' + varName;
        map.put(joined, value);
        return null;
    }
}
