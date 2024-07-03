// Time Complexity : O(max_depth)
// Space Complexity : O(nmax_depth)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 *///TC: O(n), SC: O(n)
// public class NestedIterator implements Iterator<Integer> {
//     Queue<Integer> q;
//     public NestedIterator(List<NestedInteger> nestedList) {
//         q = new LinkedList<>();
//         flattenList(nestedList);
//     }

//     @Override
//     public Integer next() {
//         return q.poll();
//     }

//     @Override
//     public boolean hasNext() {
//         return !q.isEmpty();
//     }

//     private void flattenList(List<NestedInteger> nestedList){
//         for(NestedInteger el : nestedList){
//             if(el.isInteger()){
//                 q.add(el.getInteger());
//             }
//             else{
//                 flattenList(el.getList());
//             }
//         }

//     }
// }

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());
    }

    @Override
    public Integer next() { // O(1)
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() { // O(nested depth) [[[[1]]]]
        while(!s.isEmpty()){
            if(!s.peek().hasNext()){
                s.pop();
            }
            else if((nextEl = s.peek().next()).isInteger()){
                return true;
            }
            else{
                s.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}


/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */