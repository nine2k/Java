function[absErr]= meanAbsErr(z,approxvec)
absZ=abs(z);
absErr=[];
for j=1:30
    absErr(j,1)=(sum(absZ(:,j)-approxvec))/101;
    
end