#include<bits/stdc++.h>
#define int long long int
#define bn(vec) (vec).begin(),(vec).end()
using namespace std;
signed main(){
    int right,top,myX,myY;
    char myWay;
    vector<char> way = {'E', 'S', 'W', 'N'};
    vector<vector<int> >move(1000);
    int flag[1005][1005]={0};
    move['E']={1,0};
    move['S']={0,-1};
    move['W']={-1,0};
    move['N']={0,1};
    while (cin>>right>>top)
    {
        while (cin>>myX>>myY>>myWay)
        {
            string ss;
            cin>>ss;
            for(int i=0;i<ss.size();i++){
                if(ss[i]=='R')myWay=way[(find(bn(way),myWay)-way.begin()+1)%4];
                else if(ss[i]=='L')myWay=way[(find(bn(way),myWay)-way.begin()-1+4)%4];
                else if(ss[i]=='F'){
                    if(!(myX+move[myWay][0]>right || myY+move[myWay][1]>top || myX+move[myWay][0]<0 || myY+move[myWay][1]<0)||flag[myX][myY]==0){
                        myX+=move[myWay][0];
                        myY+=move[myWay][1];
                    }
                }
                if(myX>right || myY>top || myX<0 || myY<0){
                    cout<<myX-move[myWay][0]<<" "<<myY-move[myWay][1]<<" "<<myWay<<" "<<"LOST"<<endl;
                    flag[myX-move[myWay][0]][myY-move[myWay][1]]=1;
                    break;
                }
            }
            if(!(myX>right || myY>top || myX<0 || myY<0))cout<<myX<<" "<<myY<<" "<<myWay<<endl;
        }
    }
    return 0;
}