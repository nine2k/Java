function [center,radius,r] = leastSq(group)

for j=1:100
    x=group(:,j);    
    M(j,:) = [-2*(x'),1];
    b(j,:) = -(x')*x;  
end

[Q,R] = qr(M);
y = (Q')*b;
x1=R\y;
center = [x1(1),x1(2),x1(3)]';
g=sum(center);
radius = sqrt((g*g)-x1(4));



for i=1:100
    
    e=abs(group(:,i)-center);
    direction= e/norm(e);
    difference= e-radius*direction;
    err(i,:)=sum(difference.^2);
    
    
end

r= sqrt(sum(err)/100);


