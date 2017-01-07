function [c] = Question1(P,S)
%Siyi Emily Bao, 10103388

Upper = S - P;
distance = sqrt(sum((S-P).^2));

distance = [distance;distance;distance];
result=Upper./distance
A = zeros(3);
B = zeros(3,1);
for i=1:5
    Dj = eye(3)-(result(:,i)*result(:,i)');
    D1 = Dj'*Dj;
    A = A + D1;
    D2 = Dj'*Dj*P(:,i);
    B = B + D2;  
    c = A\B;
end





    
