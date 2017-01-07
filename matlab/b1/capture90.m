function [count] = capture90(eigenvalues)

tm=sum(eigenvalues)
index=1;
count=0;
tot=0;

while (((eigenvalues(index)+tot)/tm)<0.93)  
    tot=tot+eigenvalues(index);
    index=index+1;
    count=count+1;
end
