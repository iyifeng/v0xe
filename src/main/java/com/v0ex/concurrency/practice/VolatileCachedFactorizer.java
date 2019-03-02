package com.v0ex.concurrency.practice;


import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * VolatileCachedFactorizer
 * <p/>
 * Caching the last result using a volatile reference to an immutable holder object
 *
 * @author Brian Goetz and Tim Peierls
 */
public class VolatileCachedFactorizer extends GenericServlet implements Servlet{

    private volatile OneValueCache cache = new OneValueCache(null,null);

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null){
            factors = factor(i);
            cache = new OneValueCache(i,factors);
        }
        encodeIntoResponse(res,factors);
    }

    void encodeIntoResponse(ServletResponse response,BigInteger[] factors){

    }

    BigInteger extractFromRequest(ServletRequest request){
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i){
        //Does't really factor
        return new BigInteger[]{i};
    }

}
