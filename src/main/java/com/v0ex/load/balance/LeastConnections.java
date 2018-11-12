package com.v0ex.load.balance;

/**
 * 负载均衡算法：最小连接数算法
 * @author yifeng
 * @date 17/10/9
 */
public class LeastConnections {

    /**
     * 换一个角度来说,以后端服务器的视角来观察系统的负载，而非请求发起方来观察。最小连接数法便属于此类。
     * 最小连接数算法比较灵活和智能，
     * 由于后端服务器的配置不尽相同，对于请求的处理有快有慢，
     * 它正是根据后端服务器当前的连接情况，动态地选取其中当前积压连接数最少的一台服务器来处理当前请求，
     * 尽可能地提高后端服务器的利用效率，将负载合理地分流到每一台机器。
     */
}