//
//  EEPageControlView.m
//  AgoraMiniClass
//
//  Created by yangmoumou on 2019/10/23.
//  Copyright © 2019 yangmoumou. All rights reserved.
//

#import "EEPageControlView.h"

@implementation EEPageControlView

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/
- (instancetype)initWithCoder:(NSCoder *)coder
{
    self = [super initWithCoder:coder];
    if (self) {
        [[NSBundle mainBundle] loadNibNamed:NSStringFromClass([self class]) owner:self options:nil];
           [self addSubview:self.pageControlView];
    }
    return self;
}

- (void)awakeFromNib {
    [super awakeFromNib];
    self.pageControlView.frame = self.bounds;
    self.layer.borderWidth = 1.f;
    self.layer.borderColor = RCColorWithValue(0xDBE2E5, 1.f).CGColor;
    self.layer.shadowColor = RCColorWithValue(0x000000, 1.f).CGColor;
    self.layer.shadowOffset = CGSizeMake(0.f, 2.f);
    self.layer.shadowOpacity = 2.f;
    self.layer.shadowRadius = 4.f;
    self.layer.borderWidth = 1.f;
    self.layer.cornerRadius = 6.f;
    self.layer.masksToBounds = YES;
}

- (IBAction)buttonClick:(UIButton *)sender {
    if (self.delegate) {
        if ([self.delegate respondsToSelector:@selector(previousPage)] && [sender.restorationIdentifier isEqualToString:@"previousPage"]) {
            [self.delegate previousPage];
        }
        if ([self.delegate respondsToSelector:@selector(firstPage)] && [sender.restorationIdentifier isEqualToString:@"firstPage"]) {
            [self.delegate firstPage];
        }
        if ([self.delegate respondsToSelector:@selector(nextPage)] && [sender.restorationIdentifier isEqualToString:@"nextPage"]) {
            [self.delegate nextPage];
        }
        if ([self.delegate respondsToSelector:@selector(lastPage)] && [sender.restorationIdentifier isEqualToString:@"lastPage"]) {
            [self.delegate lastPage];
        }
    }
    NSLog(@"sender-------- %@",sender.restorationIdentifier);
}


@end
