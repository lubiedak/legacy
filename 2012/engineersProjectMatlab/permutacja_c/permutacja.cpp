#include <iostream>
using namespace std;
void print(const int *a, const int size);
void permutacja(int *Value, int N, int k);
int main()
{
  const int N = 6;
  int Value[N];
  for (int i = 0; i < N; i++) {
    Value[i] = 0;
  }
  permutacja(Value, N, 0);
  system("pause");
  return 0;
}
void print(const int *a, const int size)
{
  if (a != 0) 
  {
    for (int i = 0; i < size; i++) {
      cout<< a[i] ;
  }
    cout<<endl;
  }
} 
void permutacja(int *Value, int N, int k)
{
  static int level = -1;
  level = level+1; Value[k] = level;
  if (level == N)
    print(Value, N);
  else
    for (int i = 0; i < N; i++)
      if (Value[i] == 0)
         permutacja(Value, N, i);
  level = level-1; Value[k] = 0;
}


