#include <bits/stdc++.h>
using namespace std;

struct Node{
	int x,y,z;
};
bool cmp(const Node &a,const Node &b){
    if(a.x > b.x)return false;
    else if(a.x == b.x && a.y>b.y)return false;
    else if(a.x == b.x && a.y==b.y && a.z>b.z)return false;
	else return true;
}//false會交換
signed main(){
    vector<Node> a={{1,2,3},{3,4,5},{5,6,7},{3,7,1},{2,0,518}};
    Node ans={1,2,3};
    sort(a.begin(),a.end(),cmp);
    for(auto i:a)cout<<i.x<<" "<<i.y<<" "<<i.z<<endl;
}