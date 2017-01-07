function [R,t,rms] = Question2(P,Q)
%Siyi Emily Bao, 10103388

% mean of data set P and Q
Pmean = mean(P,2);
Qmean = mean(Q,2);

% subtract mean from data
[n, m] = size(P);
A = P - Pmean*ones(1,m);
B = Q - Qmean*ones(1,m);

% convert to complex vectors
rowA = A(1,:) + i*A(2,:);
rowB = B(1,:) + i*B(2,:);

r=rowA*rowB';
r=r/norm(r);

% convert r to real 
R1 = real(r);
% convert r to imaginary 
R2 = imag(r);

R = [[R1, R2]; [-1 * R2, R1]];
t = Qmean - (R*Pmean);

% Qj=R*Pj + t
for j = 1:m
    Qj(:,j) = R*P(:,j)+t;
end

diff = Qj-Q;
rms = sqrt((sum(diff.^2,2))/size(P,2));
