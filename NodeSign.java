public class NodeSign extends NodeFact {

    private String sign;

    public NodeSign(String sign) { this.sign=sign; }

    public double eval(Environment env) throws EvalException {
        return Double.parseDouble(sign);
    }

}